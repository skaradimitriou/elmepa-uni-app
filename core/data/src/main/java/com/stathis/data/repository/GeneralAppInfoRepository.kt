package com.stathis.data.repository

import com.stathis.model.UiModel
import kotlinx.coroutines.flow.Flow

interface GeneralAppInfoRepository {

    suspend fun fetchAboutAppInfo(): Flow<List<com.stathis.model.UiModel>>
}