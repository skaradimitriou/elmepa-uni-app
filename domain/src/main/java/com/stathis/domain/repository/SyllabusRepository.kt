package com.stathis.domain.repository

import com.stathis.core.base.UiModel
import com.stathis.model.syllabus.Orientation
import com.stathis.model.syllabus.OrientationType
import kotlinx.coroutines.flow.Flow

interface SyllabusRepository {

    suspend fun fetchSemesters(): Flow<List<Orientation>>

    suspend fun fetchLessonsForSemesterAndOrientation(
        semester: String,
        orientationType: OrientationType
    ): Flow<List<UiModel>>
}