package com.stathis.domain

import com.stathis.common.base.BaseUseCase
import com.stathis.data.repository.ResearchRepository
import com.stathis.model.network.NetworkResult
import com.stathis.model.research.ResearchResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchResearchInDeptUseCase @Inject constructor(
    private val repo: ResearchRepository
) : BaseUseCase<Flow<NetworkResult<List<ResearchResponse>>>> {

    override suspend fun invoke(vararg args: Any?) = repo.fetchResearchDetails()
}