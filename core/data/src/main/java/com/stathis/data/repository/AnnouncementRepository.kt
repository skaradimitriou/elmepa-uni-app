package com.stathis.data.repository

import com.stathis.model.UiModel
import com.stathis.model.announcements.details.PostDetailsRequest
import com.stathis.model.announcements.details.PostDetailsResponse
import com.stathis.model.network.NetworkResult
import kotlinx.coroutines.flow.Flow

interface AnnouncementRepository {

    suspend fun fetchAnnouncements(forceUpdate: Boolean): Flow<NetworkResult<List<com.stathis.model.UiModel>>>

    suspend fun fetchDepartmentEvents(forceUpdate: Boolean): Flow<NetworkResult<List<com.stathis.model.UiModel>>>

    suspend fun fetchPostDetails(request: PostDetailsRequest): Flow<NetworkResult<PostDetailsResponse>>
}