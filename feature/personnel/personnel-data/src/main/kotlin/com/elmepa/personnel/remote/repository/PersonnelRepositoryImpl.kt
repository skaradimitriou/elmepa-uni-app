package com.elmepa.personnel.remote.repository

import com.elmepa.personnel.db.PersonnelDatabase
import com.elmepa.personnel.mapper.toEntity
import com.elmepa.personnel.mapper.toPerson
import com.elmepa.personnel.model.Person
import com.elmepa.personnel.remote.mapper.PersonnelMapper
import com.elmepa.personnel.remote.model.PersonnelDto
import com.elmepa.personnel.repository.PersonnelRepository
import com.google.firebase.firestore.FirebaseFirestore
import com.stathis.common.util.toListOf
import com.stathis.data.util.FULLNAME
import com.stathis.data.util.PERSONNEL_DB_PATH
import com.stathis.datastore.caching.CacheManager
import com.stathis.domain.model.DomainResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onEmpty
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

internal class PersonnelRepositoryImpl @Inject constructor(
    private val fireStore: FirebaseFirestore,
    private val localDb: PersonnelDatabase,
    private val cacheManager: CacheManager
) : PersonnelRepository {

    companion object {

        private const val PERSONNEL_CACHE_KEY = "cache_personnel"
    }

    override fun fetchAllPersonnel(): Flow<DomainResult<List<Person>>> = flow {
        val hasEmptyTimestamp = cacheManager.getCacheTimestamp(PERSONNEL_CACHE_KEY) == 0L
        val shouldFetchFromRemote = hasEmptyTimestamp || cacheManager.shouldRefresh(PERSONNEL_CACHE_KEY)

        if (shouldFetchFromRemote) {
            fetchPersonnelFromRemote()
        }

        localDb.personnelDao()
            .getAllPersonnel()
            .catch { emit(DomainResult.Error<List<Person>>(it)) }
            .collect { data ->
                if (data.isEmpty()) {
                    fetchPersonnelFromRemote()
                } else {
                    val personnel = data.map { it.toPerson() }
                    emit(DomainResult.Success<List<Person>>(personnel))
                }
            }
    }.flowOn(Dispatchers.IO)

    private suspend fun fetchPersonnelFromRemote() {
        val personnelResult = fireStore.collection(PERSONNEL_DB_PATH)
            .orderBy(FULLNAME)
            .get()
            .await()

        try {
            val queryResult = personnelResult.toListOf<PersonnelDto>()
            val result = PersonnelMapper.toDomainModel(queryResult)

            with(localDb.personnelDao()) {
                deleteAll()

                val entities = result.map { it.toEntity() }
                insertAll(entities)

                cacheManager.saveCacheTimestamp(
                    key = PERSONNEL_CACHE_KEY,
                    timestamp = System.currentTimeMillis()
                )
            }

            DomainResult.Success(result)
        } catch (e: Exception) {
            DomainResult.Error(e)
        }
    }

    override fun searchPersonnelByName(name: String): Flow<DomainResult<List<Person>>> = flow {
        localDb.personnelDao()
            .getPersonnelByFullName(name)
            .catch { emit(DomainResult.Error(Throwable("empty db"))) }
            .onEmpty {
                emit(DomainResult.Success(listOf()))
            }
            .onEach { data ->
                val people = data.map { it.toPerson() }
                emit(DomainResult.Success(people))
            }
            .firstOrNull()
    }
}
