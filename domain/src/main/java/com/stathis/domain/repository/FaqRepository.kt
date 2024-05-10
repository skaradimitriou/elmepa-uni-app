package com.stathis.domain.repository

import com.stathis.core.base.UiModel
import kotlinx.coroutines.flow.Flow

interface FaqRepository {

    suspend fun fetchFaqs(): Flow<List<UiModel>>
}