package com.stathis.common.base

interface BaseUseCase<T> {

    suspend fun invoke(vararg args: Any?): T
}