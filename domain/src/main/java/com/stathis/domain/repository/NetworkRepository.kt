package com.stathis.domain.repository

import com.stathis.model.network.NetworkResult
import kotlinx.coroutines.flow.Flow

interface NetworkRepository {

    suspend fun performReconnectingAttempt(): Flow<NetworkResult<Boolean>>
}