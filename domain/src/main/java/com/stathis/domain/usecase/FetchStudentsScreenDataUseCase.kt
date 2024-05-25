package com.stathis.domain.usecase

import com.stathis.core.base.BaseUseCase
import com.stathis.model.UiModel
import com.stathis.domain.repository.StudentsRepository
import com.stathis.model.network.NetworkResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchStudentsScreenDataUseCase @Inject constructor(
    private val repo: StudentsRepository
) : BaseUseCase<Flow<NetworkResult<List<UiModel>>>> {

    override suspend fun invoke(vararg args: Any?) = repo.fetchStudentScreenData()
}