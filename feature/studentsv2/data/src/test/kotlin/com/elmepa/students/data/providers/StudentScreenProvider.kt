package com.elmepa.students.data.providers

import com.stathis.common.R
import com.elmepa.students.data.remote.dto.StudentActionDto
import com.elmepa.students.data.remote.dto.StudentDisplayItemDto
import com.elmepa.students.data.remote.dto.StudentSectionDto
import com.elmepa.students.data.remote.dto.StudentsScreenResponseDto
import com.elmepa.students.domain.model.StudentAction
import com.elmepa.students.domain.model.StudentDisplayItem
import com.elmepa.students.domain.model.StudentSection

internal fun provideStudentScreenResponseDto(
    type: String? = null,
    url: String? = null,
    screen: String? = null
): StudentsScreenResponseDto = StudentsScreenResponseDto(
    results = listOf(
        provideStudentSectionDto(type, url, screen),
        provideStudentSectionDto(type, url, screen),
        provideStudentSectionDto(type, url, screen)
    )
)

private fun provideStudentSectionDto(
    type: String? = null,
    url: String? = null,
    screen: String? = null
): StudentSectionDto = StudentSectionDto(
    title = "title",
    options = listOf(
        provideStudentDisplayItemDto(type, url, screen)
    )
)

internal fun provideStudentDisplayItemDto(
    type: String? = null,
    url: String? = null,
    screen: String? = null
): StudentDisplayItemDto = StudentDisplayItemDto(
    title = "title",
    subtitle = "subtitle",
    action = StudentActionDto(
        type = type,
        url = url,
        screen = screen
    )
)

internal fun provideStudentScreenResponse(): List<StudentSection> = listOf(
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
            action = StudentAction.None
        )
    )
)
