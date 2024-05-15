package com.stathis.domain.usecase

import com.stathis.core.base.BaseUseCase
import com.stathis.core.base.UiModel
import com.stathis.domain.repository.SyllabusRepository
import com.stathis.model.network.NetworkResult
import com.stathis.model.syllabus.OrientationType
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchLessonsUseCase @Inject constructor(
    private val repo: SyllabusRepository
) : BaseUseCase<Flow<NetworkResult<List<UiModel>>>> {

    override suspend fun invoke(vararg args: Any?): Flow<NetworkResult<List<UiModel>>> {
        val semester = args.getOrNull(0) as? String ?: ""
        val orientation = args.getOrNull(1) as? OrientationType ?: OrientationType.UNDEFINED
        return repo.fetchLessonsForSemesterAndOrientation(semester, orientation)
    }
}