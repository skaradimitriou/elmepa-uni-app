package com.stathis.data.repository

import com.stathis.data.util.GOOGLE_URL
import com.stathis.data.util.RESPONSE_OK
import com.stathis.domain.repository.NetworkRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.jsoup.Jsoup

class NetworkRepositoryImpl : NetworkRepository {

    override suspend fun performReconnectingAttempt(): Flow<Boolean> = flow {
        try {
            val request = Jsoup.connect(GOOGLE_URL).execute()
            emit(request.statusCode() == RESPONSE_OK)
        } catch (e: Exception) {
            emit(false)
        }
    }
}