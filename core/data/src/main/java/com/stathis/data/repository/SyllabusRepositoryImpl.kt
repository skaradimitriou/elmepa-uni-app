package com.stathis.data.repository

import android.app.Application
import com.google.firebase.firestore.FirebaseFirestore
import com.stathis.common.R
import com.stathis.common.util.toNotNull
import com.stathis.data.remote.mapper.syllabus.LessonListMapper
import com.stathis.data.remote.mapper.syllabus.SyllabusRulesMapper
import com.stathis.data.remote.model.LessonDto
import com.stathis.data.remote.model.syllabus.SyllabusRuleResponseDto
import com.stathis.data.util.NAME
import com.stathis.data.util.POSTGRADUATE_SYLLABUS_DB_PATH
import com.stathis.data.util.PROGRAMME_TYPE
import com.stathis.data.util.SEMESTER
import com.stathis.data.util.SYLLABUS_RULES
import com.stathis.data.util.UNDERGRADUATE_SYLLABUS_DB_PATH
import com.stathis.model.network.NetworkResult
import com.stathis.model.syllabus.LessonHeader
import com.stathis.model.syllabus.OrientationType
import com.stathis.model.syllabus.Programme
import com.stathis.model.syllabus.ProgrammeType
import com.stathis.model.syllabus.Semester
import com.stathis.model.syllabus.SyllabusRule
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
                ProgrammeType.UNDERGRADUATE_MST -> listOf(
                    Programme(
                        title = app.getString(R.string.data_orientation),
                        type = ProgrammeType.UNDERGRADUATE_MST,
                        orientationType = OrientationType.DATA,
                        semesters = semesters,
                        isExpanded = orientationType == OrientationType.DATA
                    ),
                    Programme(
                        title = app.getString(R.string.ba_orientation),
                        type = ProgrammeType.UNDERGRADUATE_MST,
                        orientationType = OrientationType.BA,
                        semesters = semesters,
                        isExpanded = orientationType == OrientationType.BA
                    ),
                    Programme(
                        title = app.getString(R.string.mkt_orientation),
                        type = ProgrammeType.UNDERGRADUATE_MST,
                        orientationType = OrientationType.MKT,
                        semesters = semesters,
                        isExpanded = orientationType == OrientationType.MKT
                    ),
                )

                ProgrammeType.POSTGRADUATE_MST -> listOf(
                    Programme(
                        title = app.getString(R.string.postgraduate_programme),
                        semesters = semesters.take(3),
                        type = ProgrammeType.POSTGRADUATE_MST,
                        orientationType = OrientationType.POSTGRADUATE_MST,
                        isExpanded = true
                    )
                )

                else -> listOf()
            }

            emit(NetworkResult.Success(programmes))
        }

    override suspend fun fetchUndergraduateLessons(
        semesterName: String,
        programmeType: ProgrammeType,
        orientationType: OrientationType
    ): Flow<NetworkResult<List<com.stathis.model.UiModel>>> = flow {
        val queryResult = fireStore.collection(UNDERGRADUATE_SYLLABUS_DB_PATH)
            .whereEqualTo(SEMESTER, semesterName)
            .get()
            .await()
            .toObjects(LessonDto::class.java)

        val mappedResult = LessonListMapper.toDomainModel(
            dtoModel = queryResult,
            args = arrayOf(orientationType.name)
        ).filter { it.orientation.contains(orientationType) }

        val headerText = fetchSyllabusRule(programmeType = programmeType)
            .find { it.semester == semesterName }
            ?.description.toNotNull()

        val result = mutableListOf<com.stathis.model.UiModel>()

        if (headerText.isNotEmpty()) {
            result.add(LessonHeader(headerText))
        }

        if (mappedResult.isNotEmpty()) {
            result.addAll(mappedResult)
        }

        emit(NetworkResult.Success(result))
    }

    override suspend fun fetchPostgraduateLessons(
        semesterName: String,
        programmeType: ProgrammeType,
        orientationType: OrientationType
    ): Flow<NetworkResult<List<com.stathis.model.UiModel>>> = flow {
        val queryResult = fireStore.collection(POSTGRADUATE_SYLLABUS_DB_PATH)
            .whereEqualTo(SEMESTER, semesterName)
            .get()
            .await()
            .toObjects(LessonDto::class.java)

        val mappedResult = LessonListMapper.toDomainModel(
            dtoModel = queryResult,
            args = arrayOf(orientationType.name)
        )

        val headerText = fetchSyllabusRule(programmeType = programmeType)
            .find { it.semester == semesterName }
            ?.description.toNotNull()

        val result = mutableListOf<com.stathis.model.UiModel>()
        result.add(LessonHeader(headerText))
        result.addAll(mappedResult)
        emit(NetworkResult.Success(result))
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

    private suspend fun fetchSyllabusRule(programmeType: ProgrammeType): List<SyllabusRule> {
        val queryResult = fireStore.collection(SYLLABUS_RULES)
            .whereEqualTo(PROGRAMME_TYPE, programmeType.name)
            .get()
            .await()
            .toObjects(SyllabusRuleResponseDto::class.java)
            .firstOrNull()

        return SyllabusRulesMapper.toDomainModel(queryResult)
    }
}