package com.stathis.data.repository

import com.stathis.model.UiModel
import com.stathis.model.network.NetworkResult
import kotlinx.coroutines.flow.Flow

interface DepartmentRepository {

    suspend fun fetchDepartmentInformation(): Flow<NetworkResult<List<com.stathis.model.UiModel>>>

    suspend fun fetchDepartmentContactDetails(): Flow<NetworkResult<List<com.stathis.model.UiModel>>>
}