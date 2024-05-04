package com.stathis.data.datasource.remote.model

data class StudentsResponseDto(
    val carouselItems: List<CarouselItemDto>? = null,
    val links: List<LinkDto>? = null
)

data class CarouselItemDto(
    val title: String? = null,
    val webTitle: String? = null,
    val imageUrl: String? = null,
    val openUrl: String? = null,
    val position: Int? = null
)

data class LinkDto(
    val title: String? = null,
    val imageUrl: String? = null,
    val openUrl: String? = null
)