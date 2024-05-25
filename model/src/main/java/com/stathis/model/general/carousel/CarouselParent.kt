package com.stathis.model.general.carousel

import com.stathis.model.UiModel

data class CarouselParent(
    val carouselItems: List<UiModel>
) : UiModel {
    override fun equalsContent(obj: UiModel): Boolean = when (obj) {
        is CarouselParent -> carouselItems == obj.carouselItems
        else -> false
    }
}
