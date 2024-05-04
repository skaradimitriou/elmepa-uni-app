package com.stathis.model.network

sealed class NetworkResult<T> {
    class Loading<T> : NetworkResult<T>()
    class Success<T>(val data: T? = null) : NetworkResult<T>()
    class Failure<T>(val errorBody: String? = null) : NetworkResult<T>()
}