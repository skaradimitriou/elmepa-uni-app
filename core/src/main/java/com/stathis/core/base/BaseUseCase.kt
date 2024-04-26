package com.stathis.core.base

interface BaseUseCase<T> {

    suspend fun invoke(vararg args: Any?): T
}