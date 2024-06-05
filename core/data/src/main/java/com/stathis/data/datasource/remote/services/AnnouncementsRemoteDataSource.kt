package com.stathis.data.datasource.remote.services

import com.stathis.model.UiModel
import com.stathis.model.announcements.details.PostDetailsRequest
import com.stathis.model.announcements.details.PostDetailsResponse
import com.stathis.model.network.NetworkResult
import kotlinx.coroutines.flow.Flow

interface AnnouncementsRemoteDataSource {

    suspend fun fetchAnnouncementFromRemote(): Flow<NetworkResult<List<UiModel>>>

    suspend fun fetchEventsFromRemote(): Flow<NetworkResult<List<UiModel>>>

    suspend fun fetchPostDetails(request: PostDetailsRequest): Flow<NetworkResult<PostDetailsResponse>>
}