package com.stathis.domain.repository

import com.stathis.core.base.UiModel
import kotlinx.coroutines.flow.Flow

interface DashboardRepository {

    suspend fun fetchDashboardDetails(): Flow<List<UiModel>>
}