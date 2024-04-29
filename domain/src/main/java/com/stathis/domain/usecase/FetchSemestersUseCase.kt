package com.stathis.domain.usecase

import com.stathis.core.base.BaseUseCase
import com.stathis.domain.repository.SyllabusRepository
import com.stathis.model.syllabus.Semester
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchSemestersUseCase @Inject constructor(
    private val repo: SyllabusRepository
) : BaseUseCase<Flow<List<Semester>>> {

    override suspend fun invoke(vararg args: Any?) = repo.fetchSemesters()
}