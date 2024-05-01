package com.stathis.data.mappers

import com.stathis.data.datasource.remote.mapper.ContactMapper
import com.stathis.data.datasource.remote.model.ContactItemDto
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertTrue
import org.junit.Test

class ContactMapperTest {

    @Test
    fun testContactItemMapping() {
        val input: List<ContactItemDto>? = null
        val result = ContactMapper.toDomainModel(input)
        assertNotNull(result)
    }

    @Test
    fun testContactItemMappingWithValues() {
        val input = listOf(
            ContactItemDto(title = "Lorem ipsum")
        )

        val result = ContactMapper.toDomainModel(input)
        assertNotNull(result)
        assertTrue(result.getOrNull(0)?.title == "Lorem ipsum")
    }
}