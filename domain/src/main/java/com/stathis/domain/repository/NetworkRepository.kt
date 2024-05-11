package com.stathis.domain.repository

import kotlinx.coroutines.flow.Flow

interface NetworkRepository {

    suspend fun performReconnectingAttempt(): Flow<Boolean>
}