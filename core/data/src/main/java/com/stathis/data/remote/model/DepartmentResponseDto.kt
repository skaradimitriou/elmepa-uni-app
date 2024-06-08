package com.stathis.data.remote.model

data class DepartmentResponseDto(
    val carouselItems: List<CarouselItemDto>? = null,
    val syllabusItems: List<SyllabusItemDto>? = null,
    val programmes: List<ProgrammeItemDto>? = null,
    val depMembers: List<PersonnelDto>? = null,
    val social: List<SocialItemDto>? = null
)

data class SyllabusItemDto(
    val title: String? = null,
    val imageUrl: String? = null
)

data class ProgrammeItemDto(
    val title: String? = null,
    val description: String? = null,
    val imageUrl: String? = null,
    val openUrl: String? = null,
    val openInBrowser: Boolean? = null
)

data class SocialItemDto(
    val title: String? = null,
    val imageUrl: String? = null,
    val openUrl: String? = null,
    val openInBrowser: Boolean? = null
)