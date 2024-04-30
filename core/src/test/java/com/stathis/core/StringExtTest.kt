package com.stathis.core

import com.stathis.core.util.firstCharCapital
import junit.framework.TestCase.assertEquals
import org.junit.Test

class StringExtTest {

    @Test
    fun `first char capital test`() {
        val input = "hello"
        val expected = "Hello"
        val result = input.firstCharCapital()
        assertEquals(expected, result)
    }

    @Test
    fun `first char capital test with empty string`() {
        val input = ""
        val expected = ""
        val result = input.firstCharCapital()
        assertEquals(expected, result)
    }

    @Test
    fun `first char capital test with null string`() {
        val input = null
        val expected = ""
        val result = input.firstCharCapital()
        assertEquals(expected, result)
    }
}