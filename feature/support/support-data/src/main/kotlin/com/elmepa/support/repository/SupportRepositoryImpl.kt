package com.elmepa.support.repository

import com.elmepa.database.db.SupportLocalDatabase
import com.elmepa.database.ext.toApplicationForm
import com.elmepa.database.ext.toContactItem
import com.elmepa.database.ext.toEntity
import com.elmepa.database.ext.toFaq
import com.elmepa.support.model.ApplicationForm
import com.elmepa.support.model.ContactItem
import com.elmepa.support.model.Faq
import com.elmepa.support.remote.mapper.ApplicationFormMapper
import com.elmepa.support.remote.mapper.ContactMapper
import com.elmepa.support.remote.mapper.FaqMapper
import com.elmepa.support.remote.model.ApplicationFormDto
import com.elmepa.support.remote.model.FaqDto
import com.google.firebase.firestore.FirebaseFirestore
import com.stathis.common.util.toListOf
import com.stathis.data.remote.model.ContactItemDto
import com.stathis.data.util.APPLICATION_FORMS_URL
import com.stathis.data.util.CONTACT_DB_PATH
import com.stathis.data.util.DIV_CONTENT
import com.stathis.data.util.FAQ_DB_PATH
import com.stathis.data.util.FAQ_ORDER_BY_FIELD
import com.stathis.data.util.LI
import com.stathis.data.util.UL
import com.stathis.data.util.getUrlText
import com.stathis.datastore.caching.CacheManager
import com.stathis.domain.model.DomainResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await
import org.jsoup.Jsoup
import javax.inject.Inject

internal class SupportRepositoryImpl @Inject constructor(
    private val fireStore: FirebaseFirestore,
    private val supportLocalDb: SupportLocalDatabase,
    private val cacheManager: CacheManager
) : SupportRepository {

    companion object {

        private const val FAQ_CACHE_KEY = "cache_faqs"
        private const val APPLICATION_FORMS_CACHE_KEY = "cache_application_forms"
        private const val CONTACT_INFO_CACHE_KEY = "cache_contact_info"
    }

    override fun fetchFaqs(): Flow<DomainResult<List<Faq>>> = flow {
        emit(DomainResult.Loading())

        val hasEmptyTimestamp = cacheManager.getCacheTimestamp(FAQ_CACHE_KEY) == 0L
        val shouldFetchFromRemote = hasEmptyTimestamp || cacheManager.shouldRefresh(FAQ_CACHE_KEY)

        if (shouldFetchFromRemote) {
            fetchFaqsFromRemote()
        }

        supportLocalDb.faqDao()
            .getAllFaqs()
            .catch { emit(DomainResult.Error(it)) }
            .collect { data ->
                if (data.isEmpty()) {
                    fetchFaqsFromRemote()
                } else {
                    val faqs = data.map { it.toFaq() }
                    emit(DomainResult.Success(faqs))
                }
            }
    }.flowOn(Dispatchers.IO)

    private suspend fun fetchFaqsFromRemote(): DomainResult<List<Faq>> {
        val queryResult = fireStore.collection(FAQ_DB_PATH)
            .orderBy(FAQ_ORDER_BY_FIELD)
            .get()
            .await()

        val result = try {
            val queryResult = queryResult.toListOf<FaqDto>()
            val result = FaqMapper.toDomainModel(queryResult)

            with(supportLocalDb.faqDao()) {
                deleteAll()

                val entities = result.map { it.toEntity() }
                insertAll(entities)

                cacheManager.saveCacheTimestamp(
                    key = FAQ_CACHE_KEY,
                    timestamp = System.currentTimeMillis()
                )
            }

            DomainResult.Success(result)
        } catch (e: Exception) {
            DomainResult.Error(e)
        }

        return result
    }

    override fun fetchApplicationForms(): Flow<DomainResult<List<ApplicationForm>>> = flow {
        emit(DomainResult.Loading())

        val hasEmptyTimestamp = cacheManager.getCacheTimestamp(APPLICATION_FORMS_CACHE_KEY) == 0L
        val shouldFetchFromRemote = hasEmptyTimestamp || cacheManager.shouldRefresh(APPLICATION_FORMS_CACHE_KEY)

        if (shouldFetchFromRemote) {
            fetchApplicationFormsFromRemote()
        }

        supportLocalDb.applicationFormsDao()
            .getAllApplicationForms()
            .catch { emit(DomainResult.Error<List<ApplicationForm>>(it)) }
            .collect { data ->
                if (data.isEmpty()) {
                    fetchApplicationFormsFromRemote()
                } else {
                    val applicationForms = data.map { it.toApplicationForm() }
                    emit(DomainResult.Success<List<ApplicationForm>>(applicationForms))
                }
            }
    }.flowOn(Dispatchers.IO)

    override fun fetchContactInfo(): Flow<DomainResult<List<ContactItem>>> = flow {
        emit(DomainResult.Loading<List<ContactItem>>())

        val hasEmptyTimestamp = cacheManager.getCacheTimestamp(CONTACT_INFO_CACHE_KEY) == 0L
        val shouldFetchFromRemote = hasEmptyTimestamp || cacheManager.shouldRefresh(CONTACT_INFO_CACHE_KEY)

        if (shouldFetchFromRemote) {
            fetchContactItemsFromRemote()
        }

        supportLocalDb.contactItemDao()
            .getAllContactItems()
            .catch { emit(DomainResult.Error<List<ContactItem>>(it)) }
            .collect { data ->
                if (data.isEmpty()) {
                    fetchContactItemsFromRemote()
                } else {
                    val items = data.map { it.toContactItem() }
                    emit(DomainResult.Success<List<ContactItem>>(items))
                }
            }
    }

    private suspend fun fetchContactItemsFromRemote(): DomainResult<List<ContactItem>> {
        val queryResult = fireStore.collection(CONTACT_DB_PATH)
            .get()
            .await()

        val result = try {
            val dtoModels = queryResult.toListOf<ContactItemDto>()
            val domainModels = ContactMapper.toDomainModel(dtoModels)

            with(supportLocalDb.contactItemDao()) {
                deleteAll()

                val entities = domainModels.map { it.toEntity() }
                insertAll(entities)

                cacheManager.saveCacheTimestamp(
                    key = CONTACT_INFO_CACHE_KEY,
                    timestamp = System.currentTimeMillis()
                )
            }

            DomainResult.Success(domainModels)
        } catch (e: Exception) {
            DomainResult.Error(e)
        }

        return result
    }

    // Will be moved to :core:network module inside dedicated data source
    private suspend fun fetchApplicationFormsFromRemote(): DomainResult<List<ApplicationForm>> = try {
        val dtoModels = Jsoup.connect(APPLICATION_FORMS_URL).get()
            .select(DIV_CONTENT).select(UL).map { html ->
                html.select(LI).select(UL).select(LI).map { listItem ->
                    val title = listItem.text()
                    val openUrl = listItem.getUrlText()
                    ApplicationFormDto(title, openUrl)
                }
            }

        val domainModels = ApplicationFormMapper.toDomainModel(dtoModels.flatten())

        with(supportLocalDb.applicationFormsDao()) {
            deleteAll()
            insertAll(domainModels.map { it.toEntity() })

            cacheManager.saveCacheTimestamp(
                key = APPLICATION_FORMS_CACHE_KEY,
                timestamp = System.currentTimeMillis()
            )
        }

        DomainResult.Success(domainModels)
    } catch (e: Exception) {
        DomainResult.Error(e)
    }
}

