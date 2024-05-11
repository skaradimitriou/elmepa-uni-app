package com.stathis.model.general.carousel

import com.stathis.core.base.UiModel

data class CarouselItem(
    val title: String,
    val webTitle: String,
    val openUrl: String,
    val imageUrl: String,
    val position: Int,
    val openInBrowser: Boolean
) : UiModel {
    override fun equalsContent(obj: UiModel): Boolean = when (obj) {
        is CarouselItem -> title == obj.title && openUrl == obj.openUrl
                && imageUrl == obj.imageUrl && position == obj.position

        else -> false
    }
}
