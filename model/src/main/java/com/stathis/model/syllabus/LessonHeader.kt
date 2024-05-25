package com.stathis.model.syllabus

import com.stathis.model.UiModel

data class LessonHeader(
    val title: String
) : UiModel {
    override fun equalsContent(obj: UiModel): Boolean = when (obj) {
        is LessonHeader -> title == obj.title
        else -> false
    }
}