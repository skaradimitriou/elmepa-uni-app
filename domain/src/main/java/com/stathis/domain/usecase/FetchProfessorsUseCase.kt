package com.stathis.domain.usecase

import com.stathis.core.base.BaseUseCase
import com.stathis.domain.repository.ProfessorsRepository
import com.stathis.model.professors.Professor
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchProfessorsUseCase @Inject constructor(
    private val repo: ProfessorsRepository
) : BaseUseCase<Flow<List<Professor>>> {

    override suspend fun invoke(vararg args: Any?): Flow<List<Professor>> =
        repo.fetchAllProfessors()
}