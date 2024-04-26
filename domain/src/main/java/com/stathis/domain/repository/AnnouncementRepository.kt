package com.stathis.domain.repository

import com.stathis.core.base.UiModel
import kotlinx.coroutines.flow.Flow

interface AnnouncementRepository {

    suspend fun fetchAnnouncements(forceUpdate: Boolean): Flow<List<UiModel>>
}