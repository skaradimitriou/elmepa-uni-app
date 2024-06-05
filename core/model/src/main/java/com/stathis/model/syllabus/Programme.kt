package com.stathis.model.syllabus

import com.stathis.model.UiModel

data class Programme(
    val title: String,
    val type: ProgrammeType,
    val orientationType: OrientationType,
    val semesters: List<Semester>,
    var isExpanded: Boolean = false
) : UiModel {
    override fun equalsContent(obj: UiModel) = when (obj) {
        is Programme -> title == obj.title
        else -> false
    }
}