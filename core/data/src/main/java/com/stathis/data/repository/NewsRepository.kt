package com.stathis.data.repository

import androidx.paging.Pager
import com.stathis.model.announcements.Announcement
import com.stathis.model.announcements.Event
import com.stathis.model.announcements.details.PostDetailsRequest
import com.stathis.model.announcements.details.PostDetailsResponse
import com.stathis.model.network.NetworkResult
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    fun fetchAnnouncementFromRemote(): Pager<Int, Announcement>

    fun fetchEventsFromRemote(): Pager<Int, Event>

    suspend fun fetchPostDetails(request: PostDetailsRequest): Flow<NetworkResult<PostDetailsResponse>>
}