package com.students.data.providers

import com.stathis.common.R
import com.students.data.remote.dto.StudentDisplayItemDto
import com.students.data.remote.dto.StudentSectionDto
import com.students.data.remote.dto.StudentsScreenResponseDto
import com.students.domain.model.StudentDisplayItem
import com.students.domain.model.StudentSection

fun provideStudentScreenResponseDto(): StudentsScreenResponseDto = StudentsScreenResponseDto(
    results = listOf(
        provideStudentSectionDto(),
        provideStudentSectionDto(),
        provideStudentSectionDto()
    )
)

private fun provideStudentSectionDto(): StudentSectionDto = StudentSectionDto(
    title = "title",
    options = listOf(
        StudentDisplayItemDto(
            title = "title",
            subtitle = "subtitle",
            action = "action"
        )
    )
)

fun provideStudentScreenResponse(): List<StudentSection> = listOf(
    provideStudentSection(),
    provideStudentSection(),
    provideStudentSection()
)

private fun provideStudentSection(): StudentSection = StudentSection(
    title = "title",
    elements = listOf(
        StudentDisplayItem(
            title = "title",
            subtitle = "subtitle",
            icon = R.drawable.ic_info,
            action = "action"
        )
    )
)
