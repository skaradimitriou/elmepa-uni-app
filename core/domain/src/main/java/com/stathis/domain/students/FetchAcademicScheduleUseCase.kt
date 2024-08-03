package com.stathis.domain.students

import com.stathis.common.base.BaseUseCase
import com.stathis.data.repository.StudentsRepository
import com.stathis.model.UiModel
import com.stathis.model.network.NetworkResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchAcademicScheduleUseCase @Inject constructor(
    private val repository: StudentsRepository
) : BaseUseCase<Flow<NetworkResult<List<UiModel>>>> {

    override suspend fun invoke(vararg args: Any?) = repository.fetchAcademicSchedule()
}