package com.elmepa.personnel.repository

import com.stathis.domain.model.DomainResult
import com.stathis.model.UiModel
import kotlinx.coroutines.flow.Flow

interface PersonnelRepository {

    fun fetchAllPersonnel(): Flow<DomainResult<List<UiModel>>>

    fun searchPersonnelByName(name: String): Flow<DomainResult<List<UiModel>>>
}
