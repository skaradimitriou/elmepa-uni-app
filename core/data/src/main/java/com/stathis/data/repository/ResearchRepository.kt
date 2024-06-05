package com.stathis.data.repository

import com.stathis.model.network.NetworkResult
import com.stathis.model.research.ResearchResponse
import kotlinx.coroutines.flow.Flow

interface ResearchRepository {

    suspend fun fetchResearchDetails(): Flow<NetworkResult<List<ResearchResponse>>>
}