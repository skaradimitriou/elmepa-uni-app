package com.stathis.data.mappers

import com.stathis.data.datasource.remote.mapper.ProfessorsMapper
import com.stathis.data.datasource.remote.model.ProfessorDto
import junit.framework.TestCase.assertTrue
import org.junit.Test

class ProfessorsMapperTest {

    @Test
    fun testNullProfessorDtoTest() {
        val input: List<ProfessorDto>? = null
        val mappedInput = ProfessorsMapper.toDomainModel(input)
        assertTrue(mappedInput.isEmpty())
    }

    @Test
    fun testProfessorDtoMapping() {
        val input = listOf(
            ProfessorDto(
                fullName = "Test Testopoulos",
                gender = "Male"
            )
        )
        val mappedResult = ProfessorsMapper.toDomainModel(input)

        val fullName = mappedResult.getOrNull(0)?.fullName
        val gender = mappedResult.getOrNull(0)?.gender

        assertTrue(fullName == "Test Testopoulos" && gender == "Male")
    }
}