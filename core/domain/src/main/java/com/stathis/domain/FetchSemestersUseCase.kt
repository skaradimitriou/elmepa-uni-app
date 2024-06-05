package com.stathis.domain

import com.stathis.common.base.BaseUseCase
import com.stathis.data.repository.SyllabusRepository
import com.stathis.model.network.NetworkResult
import com.stathis.model.syllabus.OrientationType
import com.stathis.model.syllabus.Programme
import com.stathis.model.syllabus.ProgrammeType
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchSemestersUseCase @Inject constructor(
    private val repo: SyllabusRepository
) : BaseUseCase<Flow<NetworkResult<List<Programme>>>> {

    override suspend fun invoke(vararg args: Any?): Flow<NetworkResult<List<Programme>>> {
        val selectedProgramme = args.getOrNull(0) as? ProgrammeType ?: ProgrammeType.UNDEFINED
        val selectedOrientation = args.getOrNull(1) as? OrientationType ?: OrientationType.DATA
        return repo.fetchSemestersByProgrammeType(selectedProgramme, selectedOrientation)
    }
}