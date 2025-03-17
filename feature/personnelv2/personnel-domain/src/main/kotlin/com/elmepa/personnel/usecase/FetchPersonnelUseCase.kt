package com.elmepa.personnel.usecase

import com.elmepa.personnel.repository.PersonnelRepository
import com.stathis.domain.model.DomainResult
import com.stathis.model.UiModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchPersonnelUseCase @Inject constructor(private val repository: PersonnelRepository) {

    operator fun invoke(query: String? = null): Flow<DomainResult<List<UiModel>>> = query?.let { name ->
        repository.searchPersonnelByName(name)
    } ?: run {
        repository.fetchAllPersonnel()
    }
}
