package com.stathis.core.util

/**
 * Helper fun to make the first char of the word capital.
 */

fun String.firstCharCapital(): String {
    return lowercase().replaceFirstChar(Char::uppercase)
}