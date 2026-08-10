package com.elmepa.students.data.remote.mapper

import com.elmepa.students.data.providers.provideStudentScreenResponse
import com.elmepa.students.data.providers.provideStudentScreenResponseDto
import com.elmepa.students.domain.model.StudentAction
import com.elmepa.students.domain.model.StudentScreen
import org.junit.Test
import kotlin.test.assertEquals

class StudentScreenMapperTest {

    @Test
    fun `given StudentScreenResponseDto, when mapping to domain model, then return list of StudentScreenSection`() {
        val dto = provideStudentScreenResponseDto()
        val expected = provideStudentScreenResponse()
        assertEquals(expected, dto.toDomain())
    }

    @Test
    fun `given StudentDisplayItemDto with valid webview action, when mapping, then return StudentDisplayItem with OpenInWebView action`() {
        val url = "www.my-url.com"
        val dto = provideStudentScreenResponseDto(type = "webview", url = url)

        val result = dto.toDomain().first().elements.first().action

        val expected = StudentAction.OpenInWebView(title = "title", url = url)
        assertEquals(expected, result)
    }

    @Test
    fun `given StudentDisplayItemDto with invalid webview action, when mapping, then return StudentDisplayItem with None action`() {
        val dto = provideStudentScreenResponseDto(type = "webview", url = null)

        val result = dto.toDomain().first().elements.first().action

        assertEquals(StudentAction.None, result)
    }

    @Test
    fun `given StudentDisplayItemDto with valid browser action, when mapping, then return StudentDisplayItem with OpenInBrowser action`() {
        val url = "www.my-url.com"
        val dto = provideStudentScreenResponseDto(type = "browser", url = url)

        val result = dto.toDomain().first().elements.first().action

        val expected = StudentAction.OpenInBrowser(url = url)
        assertEquals(expected, result)
    }

    @Test
    fun `given StudentDisplayItemDto with invalid browser action, when mapping, then return StudentDisplayItem with None action`() {
        val dto = provideStudentScreenResponseDto(type = "browser", url = null)

        val result = dto.toDomain().first().elements.first().action

        assertEquals(StudentAction.None, result)
    }

    @Test
    fun `given StudentDisplayItemDto with valid screen action, when mapping, then return StudentDisplayItem with OpenInAppScreen action`() {
        val dto = provideStudentScreenResponseDto(type = "screen", screen = "academic_schedule")

        val result = dto.toDomain().first().elements.first().action

        assertEquals(StudentAction.OpenInAppScreen(screen = StudentScreen.AcademicSchedule), result)
    }

    @Test
    fun `given StudentDisplayItemDto with invalid screen action, when mapping, then return StudentDisplayItem with None action`() {
        val dto = provideStudentScreenResponseDto(type = "screen", screen = "screen")

        val result = dto.toDomain().first().elements.first().action

        assertEquals(StudentAction.None, result)
    }
}
