package com.stathis.data.util

import org.jsoup.nodes.Element

/**
 * Helper method to get the url text from Jsoup [Element].
 */

fun Element.getUrlText() = this.select(URL_TYPE).attr(URL_ATTR)