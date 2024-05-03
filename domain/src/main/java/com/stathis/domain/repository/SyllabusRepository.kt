package com.stathis.domain.repository

import com.stathis.model.syllabus.Orientation
import kotlinx.coroutines.flow.Flow

interface SyllabusRepository {

    suspend fun fetchSemesters(): Flow<List<Orientation>>
}