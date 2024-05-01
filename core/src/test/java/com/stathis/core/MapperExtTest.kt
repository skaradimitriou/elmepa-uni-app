package com.stathis.core

import com.stathis.core.util.toListOf
import com.stathis.core.util.toNotNull
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertTrue
import org.junit.Test

class MapperExtTest {

    @Test
    fun `toNotNull returns empty string when input is null`() {
        val input: String? = null
        assertEquals(input.toNotNull(), "")
    }

    @Test
    fun `toNotNull returns string when input is not null`() {
        val input = "Hello"
        assertEquals(input.toNotNull(), "Hello")
    }

    @Test
    fun `toNotNull returns 0 when input is null`() {
        val input: Int? = null
        val expected = 0
        val result = input.toNotNull()
        assertEquals(result, expected)
    }

    @Test
    fun `toNotNull returns 0L when input is null`() {
        val input: Long? = null
        val expected = 0L
        val result = input.toNotNull()
        assertEquals(result, expected)
    }

    @Test
    fun `toNotNull returns float when input is null`() {
        val input: Float? = null
        val expected = 0.0f
        val result = input.toNotNull()
        assertEquals(result, expected)
    }

    @Test
    fun `toNotNull returns double when input is null`() {
        val input: Double? = null
        val expected = 0.0
        val result = input.toNotNull()
        assertEquals(result, expected)
    }

    @Test
    fun `toNotNull returns false when input is null`() {
        val input: Boolean? = null
        val expected = false
        val result = input.toNotNull()
        assertEquals(result, expected)
    }

    @Test
    fun toNotNullWithNullList() {
        val list: List<String>? = null
        assertTrue(list.toNotNull().isEmpty())
    }

    @Test
    fun toNotNullWithStringList() {
        val list = listOf("one")
        assertTrue(list.toNotNull().size == 1)
    }

    @Test
    fun toListOfWithNullListTest(){
        val list : List<String?>? = null
        val mappedString = list.toListOf{ "one" }
        assertNotNull(mappedString)
    }
}