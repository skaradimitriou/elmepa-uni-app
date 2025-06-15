package com.stathis.domain.students

import com.stathis.data.repository.StudentsRepository
import javax.inject.Inject

class FetchAcademicScheduleUseCase @Inject constructor(
    private val repository: StudentsRepository
) {

    operator fun invoke() = repository.fetchAcademicSchedule()
}
