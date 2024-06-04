package com.stathis.domain.repository

import com.stathis.model.UiModel
import com.stathis.model.network.NetworkResult
import com.stathis.model.syllabus.Lesson
import com.stathis.model.syllabus.OrientationType
import com.stathis.model.syllabus.Programme
import com.stathis.model.syllabus.ProgrammeType
import kotlinx.coroutines.flow.Flow

interface SyllabusRepository {

    suspend fun fetchSemestersByProgrammeType(
        programmeType: ProgrammeType,
        orientationType: OrientationType? = null
    ): Flow<NetworkResult<List<Programme>>>

    suspend fun fetchUndergraduateLessons(
        semesterName: String,
        orientationType: OrientationType
    ): Flow<NetworkResult<List<UiModel>>>

    suspend fun fetchPostgraduateLessons(
        semesterName: String
    ): Flow<NetworkResult<List<UiModel>>>

    suspend fun fetchUndergraduateLessonDetails(
        lessonName: String
    ): Flow<NetworkResult<List<Lesson>>>

    suspend fun fetchPostgraduateLessonDetails(
        lessonName: String
    ): Flow<NetworkResult<List<Lesson>>>
}