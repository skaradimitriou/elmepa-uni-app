package com.stathis.data.datasource.remote.model

data class LessonDto(
    val name: String? = null,
    val description: String? = null,
    val hours: String? = null,
    val mandatory: Boolean? = null,
    val orientation: List<OrientationModelDto>? = null,
    val semester: String? = null
)

data class OrientationModelDto(
    val mandatory: Boolean? = null,
    val type: String? = null
)