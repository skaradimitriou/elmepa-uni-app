package com.elmepa.personnel.usecase

import com.elmepa.personnel.model.Person
import com.elmepa.personnel.repository.PersonnelRepository
import com.stathis.domain.model.DomainResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchPersonnelUseCase @Inject constructor(private val repository: PersonnelRepository) {

    operator fun invoke(query: String? = null): Flow<DomainResult<List<Person>>> = query?.let { name ->
        repository.searchPersonnelByName(name)
    } ?: run {
        repository.fetchAllPersonnel()
    }
}
