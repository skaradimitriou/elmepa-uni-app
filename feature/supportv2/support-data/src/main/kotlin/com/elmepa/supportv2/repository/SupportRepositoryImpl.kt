package com.elmepa.supportv2.repository

import com.elmepa.database.db.SupportLocalDatabase
import com.elmepa.database.ext.toApplicationForm
import com.elmepa.database.ext.toEntity
import com.elmepa.database.ext.toFaq
import com.elmepa.supportv2.model.ApplicationForm
import com.elmepa.supportv2.remote.mapper.ApplicationFormMapper
import com.elmepa.supportv2.remote.mapper.FaqMapper
import com.elmepa.supportv2.remote.model.ApplicationFormDto
import com.google.firebase.firestore.FirebaseFirestore
import com.stathis.common.util.toListOf
import com.stathis.data.remote.model.support.FaqDto
import com.stathis.data.util.APPLICATION_FORMS_URL
import com.stathis.data.util.DIV_CONTENT
import com.stathis.data.util.FAQ_DB_PATH
import com.stathis.data.util.FAQ_ORDER_BY_FIELD
import com.stathis.data.util.LI
import com.stathis.data.util.UL
import com.stathis.data.util.getUrlText
import com.stathis.datastore.caching.CacheManager
import com.stathis.domain.model.DomainResult
import com.stathis.model.support.Faq
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await
import org.jsoup.Jsoup
import java.lang.Exception
import javax.inject.Inject

internal class SupportRepositoryImpl @Inject constructor(
    private val fireStore: FirebaseFirestore,
    private val supportLocalDb: SupportLocalDatabase,
    private val cacheManager: CacheManager
) : SupportRepository {

    companion object {

        private const val FAQ_CACHE_KEY = "cache_faqs"
        private const val APPLICATION_FORMS_CACHE_KEY = "cache_application_forms"
    }

    override fun fetchFaqs(): Flow<DomainResult<List<Faq>>> = flow {
        emit(DomainResult.Loading<List<Faq>>())

        val hasEmptyTimestamp = cacheManager.getCacheTimestamp(FAQ_CACHE_KEY) == 0L
        val shouldFetchFromRemote = hasEmptyTimestamp || cacheManager.shouldRefresh(FAQ_CACHE_KEY)

        if (shouldFetchFromRemote) {
            fetchFaqsFromRemote()
        }

        supportLocalDb.faqDao()
            .getAllFaqs()
            .catch { emit(DomainResult.Error<List<Faq>>(it)) }
            .collect { data ->
                if (data.isEmpty()) {
                    fetchFaqsFromRemote()
                } else {
                    val faqs = data.map { it.toFaq() }
                    emit(DomainResult.Success<List<Faq>>(faqs))
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
    } catch (e: kotlin.Exception) {
        DomainResult.Error(e)
    }
}

