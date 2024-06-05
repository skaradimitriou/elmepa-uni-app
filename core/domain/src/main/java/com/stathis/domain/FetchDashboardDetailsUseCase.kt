package com.stathis.domain

import com.stathis.common.base.BaseUseCase
import com.stathis.data.repository.DashboardRepository
import com.stathis.model.UiModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchDashboardDetailsUseCase @Inject constructor(
    private val repo: DashboardRepository
) : BaseUseCase<Flow<List<UiModel>>> {

    override suspend fun invoke(vararg args: Any?) = repo.fetchDashboardDetails()
}