package com.stathis.model.department

import com.stathis.core.base.UiModel

data class FieldOfStudyParent(
    val syllabusItems: List<UiModel>
) : UiModel {
    override fun equalsContent(obj: UiModel) = when (obj) {
        is FieldOfStudyParent -> syllabusItems == obj.syllabusItems
        else -> false
    }
}

data class FieldOfStudy(
    val title: String,
    val imageUrl: String
) : UiModel {
    override fun equalsContent(obj: UiModel) = when (obj) {
        is FieldOfStudy -> title == obj.title
        else -> false
    }
}