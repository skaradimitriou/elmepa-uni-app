package com.students.data.remote.mapper

import com.stathis.common.R
import com.stathis.common.util.toListOf
import com.students.data.remote.dto.StudentsScreenResponseDto
import com.students.domain.model.StudentDisplayItem
import com.students.domain.model.StudentSection

internal fun StudentsScreenResponseDto.toDomain(): List<StudentSection> = results.toListOf {
    StudentSection(
        title = it.title.orEmpty(),
        elements = it.options.toListOf { element ->
            with(element) {
                StudentDisplayItem(
                    icon = icon.toIconResourceId(),
                    title = title.orEmpty(),
                    subtitle = subtitle.orEmpty(),
                    action = action.orEmpty(),
                )
            }
        }
    )
}

private fun String?.toIconResourceId(): Int = when (this) {
    "calendar" -> R.drawable.ic_info
    else -> R.drawable.ic_info
}
