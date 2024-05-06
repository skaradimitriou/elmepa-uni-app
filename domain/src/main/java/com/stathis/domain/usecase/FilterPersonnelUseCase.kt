package com.stathis.domain.usecase

import com.stathis.core.base.BaseUseCase
import com.stathis.domain.repository.PersonnelRepository
import com.stathis.model.personnel.Person
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FilterPersonnelUseCase @Inject constructor(
    private val repo: PersonnelRepository
) : BaseUseCase<Flow<List<Person>>> {

    override suspend fun invoke(vararg args: Any?): Flow<List<Person>> {
        val name = args.getOrNull(0) as? String ?: ""
        return repo.searchForPersonnel(name)
    }
}