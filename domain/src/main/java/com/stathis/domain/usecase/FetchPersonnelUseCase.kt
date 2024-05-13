package com.stathis.domain.usecase

import com.stathis.core.base.BaseUseCase
import com.stathis.core.base.UiModel
import com.stathis.domain.repository.PersonnelRepository
import com.stathis.model.network.NetworkResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchPersonnelUseCase @Inject constructor(
    private val repo: PersonnelRepository
) : BaseUseCase<Flow<NetworkResult<List<UiModel>>>> {

    override suspend fun invoke(vararg args: Any?) = repo.fetchAllPersonnel()
}