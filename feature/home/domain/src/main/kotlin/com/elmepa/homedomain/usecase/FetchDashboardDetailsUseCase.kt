package com.elmepa.homedomain.usecase

import com.elmepa.homedomain.repository.DashboardRepository
import javax.inject.Inject

class FetchDashboardDetailsUseCase @Inject constructor(
    private val repo: DashboardRepository
) {

    operator fun invoke() = repo.fetchDashboardOptions()
}
