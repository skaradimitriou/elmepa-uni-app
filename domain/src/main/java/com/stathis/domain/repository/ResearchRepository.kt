package com.stathis.domain.repository

import com.stathis.model.research.ResearchResponse
import kotlinx.coroutines.flow.Flow

interface ResearchRepository {

    suspend fun fetchResearchDetails(): Flow<List<ResearchResponse>>
}