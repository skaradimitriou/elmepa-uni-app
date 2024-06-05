package com.stathis.domain

import com.stathis.common.base.BaseUseCase
import com.stathis.data.repository.NetworkRepository
import com.stathis.model.network.NetworkResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TryReconnectingUseCase @Inject constructor(
    private val repo: NetworkRepository
) : BaseUseCase<Flow<NetworkResult<Boolean>>> {

    override suspend fun invoke(vararg args: Any?) = repo.performReconnectingAttempt()
}