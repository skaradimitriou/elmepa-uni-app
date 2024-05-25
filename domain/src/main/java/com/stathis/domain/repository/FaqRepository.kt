package com.stathis.domain.repository

import com.stathis.model.UiModel
import com.stathis.model.network.NetworkResult
import kotlinx.coroutines.flow.Flow

interface FaqRepository {

    suspend fun fetchFaqs(): Flow<NetworkResult<List<UiModel>>>
}