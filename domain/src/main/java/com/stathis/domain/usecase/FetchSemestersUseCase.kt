package com.stathis.domain.usecase

import com.stathis.core.base.BaseUseCase
import com.stathis.domain.repository.SyllabusRepository
import com.stathis.model.network.NetworkResult
import com.stathis.model.syllabus.Orientation
import com.stathis.model.syllabus.OrientationType
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchSemestersUseCase @Inject constructor(
    private val repo: SyllabusRepository
) : BaseUseCase<Flow<NetworkResult<List<Orientation>>>> {

    override suspend fun invoke(vararg args: Any?): Flow<NetworkResult<List<Orientation>>> {
        val selectedOrientation = args.getOrNull(0) as? OrientationType ?: OrientationType.DATA
        return repo.fetchSemesters(selectedOrientation)
    }
}