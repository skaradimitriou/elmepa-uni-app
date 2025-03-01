package com.elmepa.homedomain.repository

import com.elmepa.homedomain.model.DashboardCard
import kotlinx.coroutines.flow.Flow

interface DashboardRepository {

    fun fetchDashboardOptions(): Flow<List<DashboardCard>>
}
