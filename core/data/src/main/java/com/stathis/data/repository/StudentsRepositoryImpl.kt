package com.stathis.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.stathis.common.util.toNotNull
import com.stathis.data.remote.mapper.StudentsMapper
import com.stathis.data.remote.mapper.students.AcademicScheduleMapper
import com.stathis.data.remote.model.students.AcademicScheduleItemDto
import com.stathis.data.remote.model.students.StudentsResponseDto
import com.stathis.data.util.ACADEMIC_SCHEDULE_URL
import com.stathis.data.util.BODY
import com.stathis.data.util.BOLD
import com.stathis.data.util.DIV_CONTENT
import com.stathis.data.util.SCREEN_DATA
import com.stathis.data.util.STUDENTS_DB_PATH
import com.stathis.data.util.TABLE
import com.stathis.data.util.TD
import com.stathis.data.util.TR
import com.stathis.model.UiModel
import com.stathis.model.general.carousel.CarouselParent
import com.stathis.model.network.NetworkResult
import com.stathis.model.students.StudentLinkParent
import com.stathis.model.util.ShimmerGenerator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import org.jsoup.Jsoup
import javax.inject.Inject

class StudentsRepositoryImpl @Inject constructor(
    private val fireStore: FirebaseFirestore
) : StudentsRepository {

    override suspend fun fetchStudentScreenData(): Flow<NetworkResult<List<UiModel>>> = flow {
        val loadingData = listOf(
            CarouselParent(ShimmerGenerator.list),
            StudentLinkParent(ShimmerGenerator.list)
        )
        emit(NetworkResult.Loading(data = loadingData))

        val queryResult = fireStore.collection(STUDENTS_DB_PATH)
            .document(SCREEN_DATA)
            .get()
            .await()
            .toObject(StudentsResponseDto::class.java)

        val result = StudentsMapper.toDomainModel(queryResult)
        emit(NetworkResult.Success(result))
    }

    override suspend fun fetchAcademicSchedule(): Flow<NetworkResult<List<UiModel>>> = flow {
        try {
            emit(NetworkResult.Loading(ShimmerGenerator.list))
            val dtoModels = Jsoup.connect(ACADEMIC_SCHEDULE_URL).get()
                .select(DIV_CONTENT).select(TABLE).select(BODY).select(TR).map { element ->
                    val td = element.select(TD)
                    when {
                        //td is header (has "strong" css class)
                        td.getOrNull(0)?.select(BOLD)?.text().toNotNull()
                            .isNotEmpty() -> {
                            AcademicScheduleItemDto(
                                title = td.getOrNull(0)?.select(BOLD)?.text().toNotNull(),
                                isBoldTitle = true
                            )
                        }

                        //td is table entry
                        td.getOrNull(0)?.text().toNotNull().isNotEmpty() -> {
                            AcademicScheduleItemDto(
                                entry = td.getOrNull(0)?.text()?.trimEnd(),
                                startDate = td.getOrNull(1)?.text()?.trimEnd(),
                                endDate = td.getOrNull(2)?.text()?.trimEnd()
                            )
                        }

                        else -> AcademicScheduleItemDto()
                    }
                }

            val domainModel = AcademicScheduleMapper.toDomainModel(dtoModels)
            emit(NetworkResult.Success(domainModel))
        } catch (e: Exception) {
            emit(NetworkResult.Failure(e.localizedMessage))
        }
    }
}