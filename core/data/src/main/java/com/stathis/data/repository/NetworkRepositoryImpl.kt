package com.stathis.data.repository

import com.stathis.data.util.GOOGLE_URL
import com.stathis.data.util.RESPONSE_OK
import com.stathis.model.network.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.jsoup.Jsoup

class NetworkRepositoryImpl : NetworkRepository {

    override suspend fun performReconnectingAttempt(): Flow<NetworkResult<Boolean>> = flow {
        emit(NetworkResult.Loading())

        try {
            val request = Jsoup.connect(GOOGLE_URL).execute()
            emit(NetworkResult.Success(request.statusCode() == RESPONSE_OK))
        } catch (e: Exception) {
            emit(NetworkResult.Failure("FAILED TO CONNECT"))
        }
    }
}