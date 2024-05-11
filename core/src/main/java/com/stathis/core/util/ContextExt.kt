package com.stathis.core.util

import android.content.Context
import androidx.recyclerview.widget.GridLayoutManager

/**
 * Helper method to customize Grid Layout for a recyclerView in order to set FULL_WIDTH
 * to the first element of the list.
 */

fun Context.setupDashboardGridLayout() = GridLayoutManager(this, 2).apply {
    spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
        override fun getSpanSize(position: Int): Int {
            return if (position == 0 || position == 5) 2 else 1
        }
    }
}