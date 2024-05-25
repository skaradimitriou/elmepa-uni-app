package com.stathis.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.stathis.model.UiModel
import com.stathis.data.datasource.local.personnel.PersonnelDatabase
import com.stathis.data.datasource.remote.mapper.PersonnelMapper
import com.stathis.data.datasource.remote.model.PersonnelDto
import com.stathis.data.util.FULLNAME
import com.stathis.data.util.PERSONNEL_DB_PATH
import com.stathis.domain.repository.PersonnelRepository
import com.stathis.model.network.NetworkResult
import com.stathis.model.util.ShimmerGenerator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class PersonnelRepositoryImpl @Inject constructor(
    private val fireStore: FirebaseFirestore,
    localDataSource: PersonnelDatabase
) : PersonnelRepository {

    private val personnelDao = localDataSource.personnelDao()

    override suspend fun fetchAllPersonnel(): Flow<NetworkResult<List<UiModel>>> = flow {
        emit(NetworkResult.Loading(ShimmerGenerator.list))

        val result = fireStore.collection(PERSONNEL_DB_PATH)
            .orderBy(FULLNAME)
            .get()
            .await()
            .toObjects(PersonnelDto::class.java)

        val personnel = PersonnelMapper.toDomainModel(result)

        personnelDao.deleteAll()
        personnelDao.insertAll(personnel)

        personnelDao.getAllPersonnel().collect {
            emit(NetworkResult.Success(personnel))
        }
    }

    override suspend fun searchForPersonnel(name: String): Flow<NetworkResult<List<UiModel>>> =
        flow {
            personnelDao.queryPersonnelByFullname(name).collect { results ->
                emit(NetworkResult.Success(results))
            }
        }
}