package com.stathis.core.util

import android.webkit.WebView
import androidx.recyclerview.widget.RecyclerView
import com.stathis.core.decorations.CustomItemDecoration

/**
 * Helper Method to setup spaces between items inside a [RecyclerView].
 */

fun RecyclerView.setupItemDecoration(
    top: Int = 0,
    start: Int = 0,
    end: Int = 0,
    bottom: Int = 0
) {
    val decor = CustomItemDecoration(top, start, end, bottom)
    addItemDecoration(decor)
}

fun WebView.enableJS() = apply { settings.javaScriptEnabled = true }