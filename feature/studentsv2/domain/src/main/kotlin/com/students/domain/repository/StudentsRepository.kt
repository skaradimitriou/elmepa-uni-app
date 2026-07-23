package com.students.domain.repository

import com.stathis.domain.model.DomainResult
import com.students.domain.model.StudentSection
import kotlinx.coroutines.flow.Flow

interface StudentsRepository {

    suspend fun getStudentScreenInfo(): Flow<DomainResult<List<StudentSection>>>
}
