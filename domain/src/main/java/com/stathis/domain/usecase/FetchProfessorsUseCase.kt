package com.stathis.domain.usecase

import com.stathis.core.base.BaseUseCase
import com.stathis.core.base.UiModel
import com.stathis.domain.repository.ProfessorsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchProfessorsUseCase @Inject constructor(
    private val repo: ProfessorsRepository
) : BaseUseCase<Flow<List<UiModel>>> {

    override suspend fun invoke(vararg args: Any?): Flow<List<UiModel>> = repo.fetchAllProfessors()
}