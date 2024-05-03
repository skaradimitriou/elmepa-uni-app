package com.stathis.data.repository

import android.app.Application
import com.google.firebase.firestore.FirebaseFirestore
import com.stathis.core.R
import com.stathis.core.base.UiModel
import com.stathis.data.datasource.remote.mapper.LessonMapper
import com.stathis.data.datasource.remote.model.LessonDto
import com.stathis.domain.repository.SyllabusRepository
import com.stathis.model.syllabus.LessonHeader
import com.stathis.model.syllabus.Orientation
import com.stathis.model.syllabus.OrientationType
import com.stathis.model.syllabus.Semester
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class SyllabusRepositoryImpl @Inject constructor(
    private val app: Application,
    private val fireStore: FirebaseFirestore
) : SyllabusRepository {

    override suspend fun fetchSemesters(): Flow<List<Orientation>> = flow {
        val semesters = listOf(
            Semester("Εξάμηνο Α'"),
            Semester("Εξάμηνο Β'"),
            Semester("Εξάμηνο Γ'"),
            Semester("Εξάμηνο Δ'"),
            Semester("Εξάμηνο Ε'"),
            Semester("Εξάμηνο ΣΤ'"),
            Semester("Εξάμηνο Ζ'"),
            Semester("Εξάμηνο Η'"),
        )

        val orientations = listOf(
            Orientation(
                title = app.getString(com.stathis.core.R.string.data_orientation),
                semesters = semesters,
                type = OrientationType.DATA,
                isExpanded = true
            ),
            Orientation(
                title = app.getString(com.stathis.core.R.string.ba_orientation),
                semesters = semesters,
                type = OrientationType.BA
            ),
            Orientation(
                title = app.getString(com.stathis.core.R.string.mkt_orientation),
                semesters = semesters,
                type = OrientationType.MKT
            ),
        )

        emit(orientations)
    }

    override suspend fun fetchLessonsForSemesterAndOrientation(
        semester: String,
        orientationType: OrientationType
    ): Flow<List<UiModel>> = flow {
        val queryResult = fireStore.collection("undergraduate_lessons")
            .whereEqualTo("semester", semester)
            .whereArrayContains("orientation", orientationType)
            .get()
            .await()
            .toObjects(LessonDto::class.java)

        val mappedResult = LessonMapper.toDomainModel(queryResult)


        val headerText = if (mappedResult.all { it.mandatory }) {
            app.getString(R.string.all_lessons_mandatory)
        } else {
            app.getString(R.string.some_lessons_mandatory)
        }

        val result = mutableListOf<UiModel>()
        result.add(LessonHeader(headerText))
        result.addAll(mappedResult)
        emit(result)
    }
}