package com.stathis.data.repository

import android.app.Application
import com.google.firebase.firestore.FirebaseFirestore
import com.stathis.core.R
import com.stathis.data.datasource.remote.mapper.LessonListMapper
import com.stathis.data.datasource.remote.model.LessonDto
import com.stathis.data.util.NAME
import com.stathis.data.util.POSTGRADUATE_SYLLABUS_DB_PATH
import com.stathis.data.util.SEMESTER
import com.stathis.data.util.UNDERGRADUATE_SYLLABUS_DB_PATH
import com.stathis.domain.repository.SyllabusRepository
import com.stathis.model.UiModel
import com.stathis.model.network.NetworkResult
import com.stathis.model.syllabus.LessonHeader
import com.stathis.model.syllabus.OrientationType
import com.stathis.model.syllabus.Programme
import com.stathis.model.syllabus.ProgrammeType
import com.stathis.model.syllabus.Semester
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class SyllabusRepositoryImpl @Inject constructor(
    private val app: Application,
    private val fireStore: FirebaseFirestore
) : SyllabusRepository {

    override suspend fun fetchSemestersByProgrammeType(
        programmeType: ProgrammeType,
        orientationType: OrientationType?
    ): Flow<NetworkResult<List<Programme>>> =
        flow {
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

            val programmes = when (programmeType) {
                ProgrammeType.UNDERGRADUATE -> listOf(
                    Programme(
                        title = app.getString(R.string.data_orientation),
                        type = ProgrammeType.UNDERGRADUATE,
                        orientationType = OrientationType.DATA,
                        semesters = semesters,
                        isExpanded = orientationType == OrientationType.DATA
                    ),
                    Programme(
                        title = app.getString(R.string.ba_orientation),
                        type = ProgrammeType.UNDERGRADUATE,
                        orientationType = OrientationType.BA,
                        semesters = semesters,
                        isExpanded = orientationType == OrientationType.BA
                    ),
                    Programme(
                        title = app.getString(R.string.mkt_orientation),
                        type = ProgrammeType.UNDERGRADUATE,
                        orientationType = OrientationType.MKT,
                        semesters = semesters,
                        isExpanded = orientationType == OrientationType.MKT
                    ),
                )

                ProgrammeType.POSTGRADUATE -> listOf(
                    Programme(
                        title = app.getString(R.string.postgraduate_programme),
                        semesters = semesters.take(3),
                        type = ProgrammeType.POSTGRADUATE,
                        orientationType = OrientationType.UNDEFINED,
                        isExpanded = true
                    )
                )

                else -> listOf()
            }

            emit(NetworkResult.Success(programmes))
        }

    override suspend fun fetchUndergraduateLessons(
        semesterName: String,
        orientationType: OrientationType
    ): Flow<NetworkResult<List<UiModel>>> = flow {
        val queryResult = fireStore.collection(UNDERGRADUATE_SYLLABUS_DB_PATH)
            .whereEqualTo(SEMESTER, semesterName)
            .get()
            .await()
            .toObjects(LessonDto::class.java)

        val mappedResult = LessonListMapper.toDomainModel(
            dtoModel = queryResult,
            args = arrayOf(orientationType.name)
        ).filter { it.orientation.contains(orientationType) }

        val headerText = if (mappedResult.all { it.mandatory }) {
            app.getString(R.string.all_lessons_mandatory)
        } else {
            app.getString(R.string.some_lessons_mandatory)
        }

        val result = mutableListOf<UiModel>()
        result.add(LessonHeader(headerText))
        result.addAll(mappedResult)
        emit(NetworkResult.Success(result))
    }

    override suspend fun fetchPostgraduateLessons(
        semesterName: String
    ): Flow<NetworkResult<List<UiModel>>> = flow {
        val queryResult = fireStore.collection(POSTGRADUATE_SYLLABUS_DB_PATH)
            .whereEqualTo(SEMESTER, semesterName)
            .get()
            .await()
            .toObjects(LessonDto::class.java)

        val mappedResult = LessonListMapper.toDomainModel(dtoModel = queryResult)
        emit(NetworkResult.Success(mappedResult))
    }

    override suspend fun fetchUndergraduateLessonDetails(lessonName: String) = flow {
        emit(NetworkResult.Loading())
        val queryResult = fireStore.collection(UNDERGRADUATE_SYLLABUS_DB_PATH)
            .whereEqualTo(NAME, lessonName)
            .limit(1)
            .get()
            .await()
            .toObjects(LessonDto::class.java)

        val mappedResult = LessonListMapper.toDomainModel(queryResult)
        emit(NetworkResult.Success(mappedResult))
    }

    override suspend fun fetchPostgraduateLessonDetails(lessonName: String) = flow {
        emit(NetworkResult.Loading())
        val queryResult = fireStore.collection(POSTGRADUATE_SYLLABUS_DB_PATH)
            .whereEqualTo(NAME, lessonName)
            .limit(1)
            .get()
            .await()
            .toObjects(LessonDto::class.java)

        val mappedResult = LessonListMapper.toDomainModel(queryResult)
        emit(NetworkResult.Success(mappedResult))
    }
}