package com.stathis.model.students

import com.stathis.core.base.UiModel


data class Link(
    val title: String,
    val imageUrl: String,
    val openUrl: String,
    val openInBrowser: Boolean
) : UiModel {
    override fun equalsContent(obj: UiModel) = when (obj) {
        is Link -> title == obj.title
        else -> false
    }
}