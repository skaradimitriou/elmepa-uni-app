package com.stathis.data.repository

import com.stathis.model.UiModel
import kotlinx.coroutines.flow.Flow

interface DashboardRepository {

    suspend fun fetchDashboardDetails(): Flow<List<com.stathis.model.UiModel>>
}