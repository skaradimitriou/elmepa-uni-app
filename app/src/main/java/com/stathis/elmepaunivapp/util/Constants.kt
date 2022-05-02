package com.stathis.elmepaunivapp.util

import androidx.annotation.ColorInt
import com.google.android.material.snackbar.Snackbar

const val DEFAULT_URL = "https://mst.hmu.gr/"
const val BASE_URL = "https://mst.hmu.gr/news_gr/"
const val DATA_TYPE = "article"

const val IMG_HTML_TAG = "a.entry-featured-image-url"
const val IMG_TYPE = "img"
const val IMG_SOURCE = "src"

const val TITLE_HTML_TAG = "h2.entry-title"
const val TITLE_TYPE ="h2"

const val URL_HTML_TAG = "h2.entry-title"
const val URL_TYPE = "a"
const val URL_ATTR = "href"

const val VIRTUAL_TOUR_URL = "https://mst.hmu.gr/hmutour"
const val SCHEDULE_URL = "https://mst.hmu.gr/proptyxiako/orologio-programma-mathimaton/"

const val SECRETARY_MAIL = "kalarhaki@hmu.gr"
const val SECRETARY_TEL = "tel:2841091103"

const val URL = "URL"
const val TITLE = "TITLE"
const val DEFAULT_WEB_TITLE = "ΔΕΤ Αγ. Νικόλαος"
const val ANNOUNCEMENT = "Ανακοίνωση"

const val ABOUT_MOBILE_APP_URL = "https://mst.hmu.gr/ypiresies/mobile-epharmogh-tmhmatos/"

fun Snackbar.withColor(@ColorInt colorInt: Int): Snackbar{
    this.view.setBackgroundColor(colorInt)
    return this
}