package com.stathis.domain.usecase

import com.stathis.core.base.BaseUseCase
import com.stathis.domain.repository.NetworkRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TryReconnectingUseCase @Inject constructor(
    private val repo: NetworkRepository
) : BaseUseCase<Flow<Boolean>> {

    override suspend fun invoke(vararg args: Any?) = repo.performReconnectingAttempt()
}