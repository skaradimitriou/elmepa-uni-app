package com.students.domain.model

data class StudentSection(
    val title: String,
    val elements: List<StudentDisplayItem>
)

data class StudentDisplayItem(
    val icon: Int,
    val title: String,
    val subtitle: String,
    val action: String
)
