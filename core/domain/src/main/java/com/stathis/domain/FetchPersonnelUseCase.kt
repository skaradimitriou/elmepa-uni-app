package com.stathis.domain

import com.stathis.common.base.BaseUseCase
import com.stathis.data.repository.PersonnelRepository
import com.stathis.model.UiModel
import com.stathis.model.network.NetworkResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchPersonnelUseCase @Inject constructor(
    private val repo: PersonnelRepository
) : BaseUseCase<Flow<NetworkResult<List<UiModel>>>> {

    override suspend fun invoke(vararg args: Any?) = repo.fetchAllPersonnel()
}