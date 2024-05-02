package com.stathis.domain.repository

import com.stathis.core.base.UiModel
import com.stathis.model.professors.Professor
import kotlinx.coroutines.flow.Flow

interface ProfessorsRepository {

    suspend fun fetchAllProfessors(): Flow<List<UiModel>>

    suspend fun searchForProfessor(name: String): Flow<List<Professor>>
}