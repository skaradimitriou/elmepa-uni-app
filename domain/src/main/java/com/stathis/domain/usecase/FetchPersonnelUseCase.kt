package com.stathis.domain.usecase

import com.stathis.core.base.BaseUseCase
import com.stathis.core.base.UiModel
import com.stathis.domain.repository.PersonnelRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchPersonnelUseCase @Inject constructor(
    private val repo: PersonnelRepository
) : BaseUseCase<Flow<List<UiModel>>> {

    override suspend fun invoke(vararg args: Any?): Flow<List<UiModel>> = repo.fetchAllPersonnel()
}