package com.stathis.data.repository

import com.stathis.model.UiModel
import com.stathis.model.network.NetworkResult
import kotlinx.coroutines.flow.Flow

interface PersonnelRepository {

    suspend fun fetchAllPersonnel(): Flow<NetworkResult<List<UiModel>>>

    suspend fun searchForPersonnel(name: String): Flow<NetworkResult<List<UiModel>>>
}