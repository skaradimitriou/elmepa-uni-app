package com.elmepa.news.repository

import androidx.paging.PagingData
import com.elmepa.news.model.Announcement
import com.elmepa.news.model.Event
import com.stathis.model.network.NetworkResult
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    fun fetchAnnouncementFromRemote(): Flow<PagingData<Announcement>>

    fun fetchEventsFromRemote(): Flow<PagingData<Event>>

    fun fetchPostDetails(urlToScrape: String): Flow<NetworkResult<String>>
}
