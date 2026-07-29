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
    "book_open" -> R.drawable.ic_book_open
    "bus" -> R.drawable.ic_bus
    "calendar_days" -> R.drawable.ic_calendar_days
    "calendar_search" -> R.drawable.ic_calendar_search
    "handshake" -> R.drawable.ic_handshake
    "id_card" -> R.drawable.ic_id_card
    "info" -> R.drawable.ic_info
    "library" -> R.drawable.ic_library
    "link" -> R.drawable.ic_link
    "mail" -> R.drawable.ic_mail
    "rocket" -> R.drawable.ic_rocket
    "system" -> R.drawable.ic_system
    else -> R.drawable.ic_info
}
