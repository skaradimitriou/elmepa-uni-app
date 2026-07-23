package com.students.data.remote.dto

data class StudentsScreenResponseDto(
    val results: List<StudentSectionDto>? = null
)

data class StudentSectionDto(
    val title: String? = null,
    val options: List<StudentDisplayItemDto>? = null
)

data class StudentDisplayItemDto(
    val title: String? = null,
    val subtitle: String? = null,
    val action: String? = null
)
