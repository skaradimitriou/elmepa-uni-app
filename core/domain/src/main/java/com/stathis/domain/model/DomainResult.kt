package com.stathis.domain.model

sealed class DomainResult<T> {

    class Loading<T> : DomainResult<T>()

    data class Success<T>(val data: T) : DomainResult<T>()

    data class Error<T>(val exception: Throwable) : DomainResult<T>()
}
