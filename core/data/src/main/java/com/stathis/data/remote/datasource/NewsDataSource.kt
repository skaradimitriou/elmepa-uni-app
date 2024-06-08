package com.stathis.data.remote.datasource

import com.stathis.model.announcements.Announcement
import com.stathis.model.announcements.Event

interface NewsDataSource {

    suspend fun fetchAnnouncementFromRemote(page: Int): List<Announcement>

    suspend fun fetchEventsFromRemote(page: Int): List<Event>
}