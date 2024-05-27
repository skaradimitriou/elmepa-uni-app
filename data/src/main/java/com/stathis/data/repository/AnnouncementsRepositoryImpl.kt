package com.stathis.data.repository

import com.stathis.core.util.toNotNull
import com.stathis.data.datasource.datastore.AnnouncementsDataStore
import com.stathis.data.datasource.local.announcements.AnnouncementsDatabase
import com.stathis.data.datasource.remote.services.AnnouncementsRemoteDataSource
import com.stathis.data.util.ANNOUNCEMENTS_DS_KEY
import com.stathis.data.util.EVENTS_DS_KEY
import com.stathis.domain.repository.AnnouncementRepository
import com.stathis.model.UiModel
import com.stathis.model.announcements.details.PostDetailsRequest
import com.stathis.model.network.NetworkResult
import com.stathis.model.util.ShimmerGenerator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AnnouncementsRepositoryImpl @Inject constructor(
    localDataSource: AnnouncementsDatabase,
    private val remoteDataSource: AnnouncementsRemoteDataSource,
    private val dataStore: AnnouncementsDataStore
) : AnnouncementRepository {

    private val announcementsDao = localDataSource.announcementDao()
    private val eventsDao = localDataSource.eventsDao()

    private val refreshTime = 5 * 60 * 1000 * 1000 * 1000L

    override suspend fun fetchAnnouncements(forceUpdate: Boolean): Flow<NetworkResult<List<UiModel>>> =
        flow {
            emit(NetworkResult.Loading(ShimmerGenerator.list))

            val updateTime = dataStore.getUpdateTime(ANNOUNCEMENTS_DS_KEY).firstOrNull().toNotNull()
            val currentTime = System.nanoTime()

            if (forceUpdate) {
                remoteDataSource.fetchAnnouncementFromRemote().collect { emit(it) }
            } else {
                if (updateTime > 0 && currentTime - updateTime < refreshTime) {
                    announcementsDao.getAll().collect { dataFromLocalDb ->
                        if (dataFromLocalDb.isEmpty()) {
                            remoteDataSource.fetchAnnouncementFromRemote().collect { emit(it) }
                        } else {
                            emit(NetworkResult.Success(dataFromLocalDb))
                        }
                    }
                } else {
                    remoteDataSource.fetchAnnouncementFromRemote().collect { emit(it) }
                }
            }
        }

    override suspend fun fetchDepartmentEvents(forceUpdate: Boolean): Flow<NetworkResult<List<UiModel>>> =
        flow {
            emit(NetworkResult.Loading(ShimmerGenerator.list))

            val updateTime = dataStore.getUpdateTime(EVENTS_DS_KEY).firstOrNull().toNotNull()
            val currentTime = System.nanoTime()

            if (forceUpdate) {
                remoteDataSource.fetchEventsFromRemote().collect { emit(it) }
            } else {
                if (updateTime > 0 && currentTime - updateTime < refreshTime) {
                    eventsDao.getAll().collect { dataFromLocalDb ->
                        if (dataFromLocalDb.isEmpty()) {
                            remoteDataSource.fetchEventsFromRemote().collect { emit(it) }
                        } else {
                            emit(NetworkResult.Success(dataFromLocalDb))
                        }
                    }
                } else {
                    remoteDataSource.fetchEventsFromRemote().collect { emit(it) }
                }
            }
        }

    override suspend fun fetchPostDetails(
        request: PostDetailsRequest
    ) = remoteDataSource.fetchPostDetails(request)
}