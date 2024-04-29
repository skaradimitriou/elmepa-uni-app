package com.stathis.core

import com.stathis.core.util.firstCharCapital
import junit.framework.TestCase.assertEquals
import org.junit.Test

class StringExtTest {

    //unit test for firstCharCapital
    @Test
    fun `firstCharCapital returns the first char of the word capital`() {
        val word = "hello"
        val result = word.firstCharCapital()
        assertEquals(result, "Hello")
    }

    //make firstCharLetter input nullable
    @Test
    fun `firstCharCapital returns the first char of the word capital when input is null`() {
        val word: String? = null
        val result = word.firstCharCapital()
        assertEquals(result, "")
    }
}