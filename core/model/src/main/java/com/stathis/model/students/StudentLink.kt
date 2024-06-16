package com.stathis.model.students

import com.stathis.model.UiModel


data class StudentLink(
    val title: String,
    val imageUrl: String,
    val openUrl: String,
    val openInBrowser: Boolean
) : UiModel {
    override fun equalsContent(obj: UiModel) = when (obj) {
        is StudentLink -> title == obj.title
        else -> false
    }
}