package com.stathis.model.general.carousel

import com.stathis.model.UiModel
import com.stathis.model.navigation.NavigationAction

data class CarouselItem(
    val title: String,
    val webTitle: String,
    val openUrl: String,
    val imageUrl: String,
    val position: Int,
    val openInBrowser: Boolean,
    val action: NavigationAction
) : UiModel {
    override fun equalsContent(obj: UiModel): Boolean = when (obj) {
        is CarouselItem -> title == obj.title && openUrl == obj.openUrl
                && imageUrl == obj.imageUrl && position == obj.position

        else -> false
    }
}
