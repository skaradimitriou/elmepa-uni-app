package com.stathis.domain.usecase

import com.stathis.core.base.BaseUseCase
import com.stathis.domain.repository.ResearchRepository
import com.stathis.model.research.ResearchResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchResearchInDeptUseCase @Inject constructor(
    private val repo: ResearchRepository
) : BaseUseCase<Flow<List<ResearchResponse>>> {

    override suspend fun invoke(vararg args: Any?) = repo.fetchResearchDetails()
}