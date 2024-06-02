package com.stathis.data.datasource.remote.model

data class ResearchResponseDto(
    val categoryName: String? = null,
    val researchItems: List<ResearchItemDto>? = null
)

data class ResearchItemDto(
    val name: String? = null,
    val url: String? = null,
    val imageResource: String? = null,
    val openInBrowser: Boolean? = null
)