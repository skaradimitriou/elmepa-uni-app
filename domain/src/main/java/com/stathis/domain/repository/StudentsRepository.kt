package com.stathis.domain.repository

import com.stathis.model.UiModel
import com.stathis.model.network.NetworkResult
import kotlinx.coroutines.flow.Flow

interface StudentsRepository {

    suspend fun fetchStudentScreenData(): Flow<NetworkResult<List<UiModel>>>
}