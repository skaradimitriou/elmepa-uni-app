package com.stathis.model.syllabus

import com.stathis.model.UiModel

data class Lesson(
    var name: String,
    val description: String,
    val hours: String,
    val mandatory: Boolean,
    val orientation: List<OrientationType>,
    val semester: String,
    val credits: String
) : UiModel {

    override fun equalsContent(obj: UiModel) = when (obj) {
        is Lesson -> name == obj.name && description == obj.description
        else -> false
    }
}
