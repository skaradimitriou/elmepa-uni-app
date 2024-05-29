package com.stathis.domain.repository

import com.stathis.model.UiModel
import kotlinx.coroutines.flow.Flow

interface GeneralAppInfoRepository {

    suspend fun fetchAboutAppInfo(): Flow<List<UiModel>>
}