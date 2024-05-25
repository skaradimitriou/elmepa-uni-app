package com.stathis.model.syllabus

import com.stathis.model.UiModel

data class Orientation(
    val title: String,
    val semesters: List<Semester>,
    val type: OrientationType,
    var isExpanded: Boolean = false
) : UiModel {
    override fun equalsContent(obj: UiModel) = when (obj) {
        is Orientation -> title == obj.title
        else -> false
    }
}