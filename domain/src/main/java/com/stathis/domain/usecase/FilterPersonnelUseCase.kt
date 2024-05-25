package com.stathis.domain.usecase

import com.stathis.core.base.BaseUseCase
import com.stathis.model.UiModel
import com.stathis.domain.repository.PersonnelRepository
import com.stathis.model.network.NetworkResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FilterPersonnelUseCase @Inject constructor(
    private val repo: PersonnelRepository
) : BaseUseCase<Flow<NetworkResult<List<UiModel>>>> {

    override suspend fun invoke(vararg args: Any?): Flow<NetworkResult<List<UiModel>>> {
        val name = args.getOrNull(0) as? String ?: ""
        return repo.searchForPersonnel(name)
    }
}