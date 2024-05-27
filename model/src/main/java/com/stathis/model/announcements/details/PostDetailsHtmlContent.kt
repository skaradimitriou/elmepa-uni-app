package com.stathis.model.announcements.details

import com.stathis.model.UiModel

data class PostDetailsHtmlContent(
    val content: String
) : UiModel {
    override fun equalsContent(obj: UiModel) = false
}