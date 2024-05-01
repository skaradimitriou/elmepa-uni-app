package com.stathis.core

import com.stathis.core.util.makeFirstCharCapital
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertTrue
import org.junit.Test

class StringExtTest {

    @Test
    fun testCapitalCaseWithString_ReturnsTrue() {
        val input = "hello"
        val expected = "Hello"
        assertTrue(input.makeFirstCharCapital() == expected)
    }

    @Test
    fun testAllCapitalWord_ReturnsTrue() {
        val input = "WORD"
        val expected = "Word"
        assertTrue(input.makeFirstCharCapital() == expected)
    }

    @Test
    fun testMakeFirstLetterCapitalWithNullInput_ReturnsTrue() {
        val input: String? = null
        val expected = ""
        assertTrue(input.makeFirstCharCapital() == expected)
    }

    @Test
    fun testMakeFirstLetterCapitalWithNullInput() {
        val input: String? = null
        assertNotNull(input.makeFirstCharCapital())
    }

    @Test
    fun testMakeFirstLetterCapitalSameWord_ReturnsTrue() {
        val input = "Word"
        val expected = "Word"
        assertTrue(input.makeFirstCharCapital() == expected)
    }
}