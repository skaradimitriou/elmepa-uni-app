package com.stathis.model.department

import com.stathis.core.base.UiModel

data class DepartmentProgrammeItem(
    val programmes: List<UiModel>
) : UiModel {
    override fun equalsContent(obj: UiModel) = when (obj) {
        is DepartmentProgrammeItem -> programmes == obj.programmes
        else -> false
    }
}

data class Programme(
    val title: String,
    val description: String,
    val imageUrl: String,
    val openUrl: String
) : UiModel {
    override fun equalsContent(obj: UiModel) = when (obj) {
        is Programme -> title == obj.title && imageUrl == obj.imageUrl
        else -> false
    }
}