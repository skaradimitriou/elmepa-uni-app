package com.stathis.model.syllabus

import com.stathis.model.UiModel

data class Semester(
    val name: String
) : UiModel {
    override fun equalsContent(obj: UiModel): Boolean = when (obj) {
        is Semester -> name == obj.name
        else -> false
    }
}