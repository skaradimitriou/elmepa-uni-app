package com.stathis.data.mappers

import com.stathis.data.datasource.remote.mapper.PersonnelMapper
import com.stathis.data.datasource.remote.model.PersonnelDto
import junit.framework.TestCase.assertTrue
import org.junit.Test

class PersonnelMapperTest {

    @Test
    fun testNullPersonnelDtoTest() {
        val input: List<PersonnelDto>? = null
        val mappedInput = PersonnelMapper.toDomainModel(input)
        assertTrue(mappedInput.isEmpty())
    }

    @Test
    fun testPersonnelDtoMapping() {
        val input = listOf(
            PersonnelDto(
                fullName = "Test Testopoulos",
                gender = "Male"
            )
        )
        val mappedResult = PersonnelMapper.toDomainModel(input)

        val fullName = mappedResult.getOrNull(0)?.fullName
        val gender = mappedResult.getOrNull(0)?.gender

        assertTrue(fullName == "Test Testopoulos" && gender == "Male")
    }
}