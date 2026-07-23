package com.students.data.remote.mapper

import com.stathis.common.util.toListOf
import com.students.data.remote.dto.StudentsScreenResponseDto
import com.students.domain.model.StudentDisplayItem
import com.students.domain.model.StudentSection

internal fun StudentsScreenResponseDto.toDomain(): List<StudentSection> = results.toListOf {
    StudentSection(
        title = it.title.orEmpty(),
        elements = it.options.toListOf { element ->
            StudentDisplayItem(
                title = element.title.orEmpty(),
                subtitle = element.subtitle.orEmpty(),
                action = element.action.orEmpty(),
            )
        }
    )
}
