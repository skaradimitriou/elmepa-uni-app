package com.stathis.domain.usecase

import com.stathis.core.base.BaseUseCase
import com.stathis.domain.repository.SyllabusRepository
import com.stathis.model.network.NetworkResult
import com.stathis.model.syllabus.Lesson
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchLessonDetailsUseCase @Inject constructor(
    private val repo: SyllabusRepository
) : BaseUseCase<Flow<NetworkResult<List<Lesson>>>> {

    override suspend fun invoke(vararg args: Any?): Flow<NetworkResult<List<Lesson>>> {
        val lessonName = args.getOrNull(0) as? String ?: ""
        return repo.fetchLessonDetails(lessonName)
    }
}