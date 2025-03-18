package com.elmepa.personnel.repository

import com.elmepa.personnel.model.Person
import com.stathis.domain.model.DomainResult
import kotlinx.coroutines.flow.Flow

interface PersonnelRepository {

    fun fetchAllPersonnel(): Flow<DomainResult<List<Person>>>

    fun searchPersonnelByName(name: String): Flow<DomainResult<List<Person>>>
}
