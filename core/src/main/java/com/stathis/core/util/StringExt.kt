package com.stathis.core.util

import android.text.Html

/**
 * Helper fun to make the first char of the word capital.
 */

fun String?.makeFirstCharCapital() = toNotNull().lowercase().replaceFirstChar(Char::uppercase)

/**
 * Helper fun to remove html format from a given string.
 */

fun String?.toNonHtmlText(): String {
    return Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY).toString()
}