package com.stathis.announcements.details.generator

import com.stathis.core.util.toNotNull
import com.stathis.model.announcements.details.PostDetailsHeader
import com.stathis.model.announcements.details.PostDetailsHtmlContent
import com.stathis.model.announcements.details.PostDetailsResponse

object PostDetailsGenerator {

    fun toUiModel(response: PostDetailsResponse?) = listOf(
        PostDetailsHeader(
            title = response?.title.toNotNull(),
            image = response?.image.toNotNull(),
            pubDate = response?.pubDate.toNotNull()
        ),
        PostDetailsHtmlContent(
            content = response?.htmlContent.toNotNull()
        )
    )
}