package com.stathis.model.syllabus

import com.stathis.core.base.UiModel

data class Lesson(
    var name: String,
    val description: String,
    val hours: String,
    val mandatory: Boolean,
    val orientation: List<OrientationType>,
    val semester: String
) : UiModel {
    override fun equalsContent(obj: UiModel) = when (obj) {
        is Lesson -> name == obj.name && mandatory == obj.mandatory && description == obj.description
        else -> false
    }
}