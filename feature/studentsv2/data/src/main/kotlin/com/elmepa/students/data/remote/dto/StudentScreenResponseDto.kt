package com.elmepa.students.data.remote.dto

internal data class StudentsScreenResponseDto(
    val results: List<StudentSectionDto>? = null
)

internal data class StudentSectionDto(
    val title: String? = null,
    val options: List<StudentDisplayItemDto>? = null
)

internal data class StudentDisplayItemDto(
    val icon: String? = null,
    val title: String? = null,
    val subtitle: String? = null,
    val action: StudentActionDto? = null
)

internal data class StudentActionDto(
    val type: String? = null,
    val url: String? = null,
    val screen: String? = null
)
