package com.stathis.domain.usecase

import com.stathis.core.base.BaseUseCase
import com.stathis.core.util.toNotNull
import com.stathis.domain.repository.SyllabusRepository
import com.stathis.model.UiModel
import com.stathis.model.network.NetworkResult
import com.stathis.model.syllabus.OrientationType
import com.stathis.model.syllabus.ProgrammeType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class FetchLessonsUseCase @Inject constructor(
    private val repo: SyllabusRepository
) : BaseUseCase<Flow<NetworkResult<List<UiModel>>>> {

    override suspend fun invoke(vararg args: Any?): Flow<NetworkResult<List<UiModel>>> {
        val programme = args.getOrNull(0) as? ProgrammeType ?: ProgrammeType.UNDEFINED
        val orientation = args.getOrNull(1) as? OrientationType ?: OrientationType.UNDEFINED
        val semester = (args.getOrNull(2) as? String?).toNotNull()

        val result = when (programme) {
            ProgrammeType.UNDERGRADUATE -> repo.fetchUndergraduateLessons(
                semesterName = semester,
                orientationType = orientation
            )

            ProgrammeType.POSTGRADUATE -> repo.fetchPostgraduateLessons(
                semesterName = semester
            )

            else -> flowOf(NetworkResult.Success(listOf()))
        }

        return result
    }
}