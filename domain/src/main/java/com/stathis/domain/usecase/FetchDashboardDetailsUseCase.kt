package com.stathis.domain.usecase

import com.stathis.core.base.BaseUseCase
import com.stathis.core.base.UiModel
import com.stathis.domain.repository.DashboardRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchDashboardDetailsUseCase @Inject constructor(
    private val repo: DashboardRepository
) : BaseUseCase<Flow<List<UiModel>>> {

    override suspend fun invoke(vararg args: String?) = repo.fetchDashboardDetails()
}