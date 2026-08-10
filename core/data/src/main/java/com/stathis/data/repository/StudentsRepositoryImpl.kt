package com.stathis.data.repository

import com.stathis.common.util.toNotNull
import com.stathis.data.remote.mapper.students.AcademicScheduleMapper
import com.stathis.data.remote.model.students.AcademicScheduleItemDto
import com.stathis.data.util.ACADEMIC_SCHEDULE_URL
import com.stathis.data.util.BODY
import com.stathis.data.util.BOLD
import com.stathis.data.util.DIV_CONTENT
import com.stathis.data.util.TABLE
import com.stathis.data.util.TD
import com.stathis.data.util.TR
import com.stathis.model.UiModel
import com.stathis.model.network.NetworkResult
import com.stathis.model.util.ShimmerGenerator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.jsoup.Jsoup
import javax.inject.Inject

class StudentsRepositoryImpl @Inject constructor() : StudentsRepository {

    override fun fetchAcademicSchedule(): Flow<NetworkResult<List<UiModel>>> = flow {
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
