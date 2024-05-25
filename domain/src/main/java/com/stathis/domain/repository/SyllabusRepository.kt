package com.stathis.domain.repository

import com.stathis.model.UiModel
import com.stathis.model.network.NetworkResult
import com.stathis.model.syllabus.Lesson
import com.stathis.model.syllabus.Orientation
import com.stathis.model.syllabus.OrientationType
import kotlinx.coroutines.flow.Flow

interface SyllabusRepository {

    suspend fun fetchSemesters(selectedOrientationType: OrientationType): Flow<NetworkResult<List<Orientation>>>

    suspend fun fetchLessonsForSemesterAndOrientation(
        semester: String,
        orientationType: OrientationType
    ): Flow<NetworkResult<List<UiModel>>>

    suspend fun fetchLessonDetails(lessonName: String): Flow<NetworkResult<List<Lesson>>>
}