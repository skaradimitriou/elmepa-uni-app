package com.stathis.domain.repository

import com.stathis.model.professors.Professor
import kotlinx.coroutines.flow.Flow

interface ProfessorsRepository {

    suspend fun fetchAllProfessors(): Flow<List<Professor>>

    suspend fun searchForProfessor(name: String): Flow<List<Professor>>
}