package com.stathis.data.repository

import com.stathis.model.network.NetworkResult
import kotlinx.coroutines.flow.Flow

interface NetworkRepository {

    suspend fun performReconnectingAttempt(): Flow<NetworkResult<Boolean>>
}