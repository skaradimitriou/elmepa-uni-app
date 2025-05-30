package com.elmepa.news.remote.source

import com.elmepa.news.model.Announcement
import com.elmepa.news.model.Event

interface NewsDataSource {

    suspend fun fetchAnnouncementFromRemote(page: Int): List<Announcement>

    suspend fun fetchEventsFromRemote(page: Int): List<Event>
}
