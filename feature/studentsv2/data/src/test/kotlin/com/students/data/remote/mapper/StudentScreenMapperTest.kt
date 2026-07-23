package com.students.data.remote.mapper

import com.students.data.providers.provideStudentScreenResponse
import com.students.data.providers.provideStudentScreenResponseDto
import org.junit.Test
import kotlin.test.assertEquals

class StudentScreenMapperTest {

    @Test
    fun `given StudentScreenResponseDto, when mapping to domain model, then return list of StudentScreenSection`() {
        val dto = provideStudentScreenResponseDto()
        val expected = provideStudentScreenResponse()
        assertEquals(expected, dto.toDomain())
    }
}
