package com.stathis.domain

import com.stathis.data.repository.SyllabusRepository
import com.stathis.model.syllabus.OrientationType
import com.stathis.model.syllabus.ProgrammeType
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FetchSemestersUseCase @Inject constructor(
    private val repo: SyllabusRepository
) {

    operator fun invoke(vararg args: Any?) = flow {
        val selectedProgramme = args.getOrNull(0) as? ProgrammeType ?: ProgrammeType.UNDEFINED
        val selectedOrientation = args.getOrNull(1) as? OrientationType ?: OrientationType.DATA
        val result = repo.fetchSemestersByProgrammeType(selectedProgramme, selectedOrientation)
        emit(result)
    }.flatMapConcat { it }
}
