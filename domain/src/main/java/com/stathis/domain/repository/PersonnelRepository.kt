package com.stathis.domain.repository

import com.stathis.core.base.UiModel
import com.stathis.model.network.NetworkResult
import kotlinx.coroutines.flow.Flow

interface PersonnelRepository {

    suspend fun fetchAllPersonnel(): Flow<NetworkResult<List<UiModel>>>

    suspend fun searchForPersonnel(name: String): Flow<NetworkResult<List<UiModel>>>
}