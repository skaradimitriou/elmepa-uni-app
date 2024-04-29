package com.stathis.core

import com.stathis.core.util.toNotNull
import junit.framework.TestCase.assertEquals
import org.junit.Test

class MapperExtTest {

    @Test
    fun `toNotNull returns empty string when input is null`() {
        val input: String? = null
        val expected = ""
        val result = input.toNotNull()
        assertEquals(result, expected)
    }

    @Test
    fun `toNotNull returns string when input is not null`() {
        val input = "Hello"
        val expected = "Hello"
        val result = input.toNotNull()
        assertEquals(result, expected)
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
}