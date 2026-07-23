package com.students.domain.model

data class StudentSection(
    val title: String,
    val elements: List<StudentDisplayItem>
)

data class StudentDisplayItem(
    val title: String,
    val subtitle: String,
    val action: String
)
