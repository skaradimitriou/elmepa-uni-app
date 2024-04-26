package com.stathis.data.datasource.remote.services

import com.stathis.model.announcements.Announcement
import kotlinx.coroutines.flow.Flow

interface AnnouncementsRemoteDataSource {

    suspend fun fetchAnnouncementFromRemote(): Flow<List<Announcement>>
}