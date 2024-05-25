package com.stathis.data.repository

import com.stathis.core.util.toNotNull
import com.stathis.data.datasource.datastore.AnnouncementsDataStore
import com.stathis.data.datasource.local.announcements.AnnouncementsDao
import com.stathis.data.datasource.remote.services.AnnouncementsRemoteDataSource
import com.stathis.domain.repository.AnnouncementRepository
import com.stathis.model.UiModel
import com.stathis.model.network.NetworkResult
import com.stathis.model.util.ShimmerGenerator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AnnouncementsRepositoryImpl @Inject constructor(
    private val localDataSource: AnnouncementsDao,
    private val remoteDataSource: AnnouncementsRemoteDataSource,
    private val dataStore: AnnouncementsDataStore
) : AnnouncementRepository {

    private val refreshTime = 5 * 60 * 1000 * 1000 * 1000L

    override suspend fun fetchAnnouncements(forceUpdate: Boolean): Flow<NetworkResult<List<UiModel>>> =
        flow {
            emit(NetworkResult.Loading(ShimmerGenerator.list))

            val updateTime = dataStore.getUpdateTime().firstOrNull().toNotNull()
            val currentTime = System.nanoTime()

            if (forceUpdate) {
                fetchFromRemote().collect { emit(NetworkResult.Success(it)) }
            } else {
                if (updateTime > 0 && currentTime - updateTime < refreshTime) {
                    localDataSource.getAll().collect { dataFromLocalDb ->
                        if (dataFromLocalDb.isEmpty()) {
                            fetchFromRemote().collect { emit(NetworkResult.Success(it)) }
                        } else {
                            emit(NetworkResult.Success(dataFromLocalDb))
                        }
                    }
                } else {
                    fetchFromRemote().collect { emit(NetworkResult.Success(it)) }
                }
            }
        }

    private suspend fun fetchFromRemote() = flow {
        remoteDataSource.fetchAnnouncementFromRemote().collect { dataFromRemote ->
            localDataSource.getAll().collect { dataFromLocalDb ->
                emit(dataFromLocalDb)
            }
        }
    }
}