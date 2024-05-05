package com.stathis.data.repository

import com.stathis.core.base.UiModel
import com.stathis.core.util.SharedPreferencesHelper
import com.stathis.data.datasource.local.announcements.AnnouncementsDao
import com.stathis.data.datasource.remote.services.AnnouncementsRemoteDataSource
import com.stathis.domain.repository.AnnouncementRepository
import com.stathis.model.util.ShimmerGenerator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AnnouncementsRepositoryImpl @Inject constructor(
    private val localDataSource: AnnouncementsDao,
    private val remoteDataSource: AnnouncementsRemoteDataSource,
    private val preferences: SharedPreferencesHelper
) : AnnouncementRepository {

    private val refreshTime = 5 * 60 * 1000 * 1000 * 1000L

    override suspend fun fetchAnnouncements(forceUpdate: Boolean): Flow<List<UiModel>> = flow {
        emit(ShimmerGenerator.list)

        val updateTime = preferences.getUpdateTime()
        val currentTime = System.nanoTime()

        if (forceUpdate) {
            fetchFromRemote().collect { emit(it) }
        } else {
            if (updateTime > 0 && currentTime - updateTime < refreshTime) {
                localDataSource.getAll().collect { dataFromLocalDb ->
                    if (dataFromLocalDb.isEmpty()) {
                        fetchFromRemote().collect { emit(it) }
                    } else {
                        emit(dataFromLocalDb)
                    }
                }
            } else {
                fetchFromRemote().collect { emit(it) }
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