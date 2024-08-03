package com.stathis.model.students

import com.stathis.model.UiModel

data class AcademicScheduleTitle(
    val title: String
) : UiModel {
    override fun equalsContent(obj: UiModel) = when (obj) {
        is AcademicScheduleTitle -> title == obj.title
        else -> false
    }
}

data class AcademicScheduleEntry(
    val title: String,
    val date: String
) : UiModel {
    override fun equalsContent(obj: UiModel) = when (obj) {
        is AcademicScheduleEntry -> title == obj.title && date == obj.date
        else -> false
    }
}
