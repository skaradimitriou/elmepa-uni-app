package com.stathis.domain.repository

import com.stathis.core.base.UiModel
import com.stathis.model.personnel.Person
import kotlinx.coroutines.flow.Flow

interface PersonnelRepository {

    suspend fun fetchAllPersonnel(): Flow<List<UiModel>>

    suspend fun searchForPersonnel(name: String): Flow<List<Person>>
}