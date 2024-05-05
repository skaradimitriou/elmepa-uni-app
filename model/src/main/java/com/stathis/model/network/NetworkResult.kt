package com.stathis.model.network

sealed class NetworkResult<T> {
    data class Loading<T>(val data: T? = null) : NetworkResult<T>()
    data class Success<T>(val data: T? = null) : NetworkResult<T>()
    data class Failure<T>(val errorBody: String? = null) : NetworkResult<T>()
}