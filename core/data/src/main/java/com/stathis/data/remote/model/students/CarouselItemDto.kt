package com.stathis.data.remote.model.students

data class CarouselItemDto(
    val title: String? = null,
    val webTitle: String? = null,
    val imageUrl: String? = null,
    val openUrl: String? = null,
    val position: Int? = null,
    val openInBrowser: Boolean? = null,
    val action: String? = null
)
