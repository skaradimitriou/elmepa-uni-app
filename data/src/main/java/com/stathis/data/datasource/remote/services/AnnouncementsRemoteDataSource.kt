package com.stathis.data.datasource.remote.services

import com.stathis.model.UiModel
import com.stathis.model.network.NetworkResult
import kotlinx.coroutines.flow.Flow

interface AnnouncementsRemoteDataSource {

    suspend fun fetchAnnouncementFromRemote(): Flow<NetworkResult<List<UiModel>>>

    suspend fun fetchEventsFromRemote(): Flow<NetworkResult<List<UiModel>>>
}