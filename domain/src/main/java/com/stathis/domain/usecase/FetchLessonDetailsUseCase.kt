package com.stathis.domain.usecase

import com.stathis.core.base.BaseUseCase
import com.stathis.core.util.toNotNull
import com.stathis.domain.repository.SyllabusRepository
import com.stathis.model.network.NetworkResult
import com.stathis.model.syllabus.Lesson
import com.stathis.model.syllabus.ProgrammeType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class FetchLessonDetailsUseCase @Inject constructor(
    private val repo: SyllabusRepository
) : BaseUseCase<Flow<NetworkResult<List<Lesson>>>> {

    override suspend fun invoke(vararg args: Any?): Flow<NetworkResult<List<Lesson>>> {
        val programmeType = args.getOrNull(0) as? ProgrammeType? ?: ProgrammeType.UNDEFINED
        val lessonName = (args.getOrNull(1) as? String?).toNotNull()

        val result = when (programmeType) {
            ProgrammeType.UNDERGRADUATE -> repo.fetchUndergraduateLessonDetails(lessonName)
            ProgrammeType.POSTGRADUATE -> repo.fetchPostgraduateLessonDetails(lessonName)
            else -> flowOf(NetworkResult.Success(listOf()))
        }

        return result
    }
}