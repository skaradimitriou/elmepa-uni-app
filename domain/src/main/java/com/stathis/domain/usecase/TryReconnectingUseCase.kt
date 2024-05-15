package com.stathis.domain.usecase

import com.stathis.core.base.BaseUseCase
import com.stathis.domain.repository.NetworkRepository
import com.stathis.model.network.NetworkResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TryReconnectingUseCase @Inject constructor(
    private val repo: NetworkRepository
) : BaseUseCase<Flow<NetworkResult<Boolean>>> {

    override suspend fun invoke(vararg args: Any?) = repo.performReconnectingAttempt()
}