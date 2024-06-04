package com.stathis.core.util

import android.graphics.text.LineBreaker
import android.os.Build
import android.os.Handler
import android.view.MenuItem
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.stathis.core.adapters.CarouselAdapter
import com.stathis.core.decorations.CustomItemDecoration


/**
 * Helper Method to setup spaces between items inside a [RecyclerView].
 */

fun RecyclerView.setupItemDecoration(
    top: Int = 30,
    start: Int = 30,
    end: Int = 30,
    bottom: Int = 0
) {
    removeAllItemDecorations()
    val decor = CustomItemDecoration(top, start, end, bottom)
    addItemDecoration(decor)
}

/**
 * Helper Method to clear all item decorations of a [RecyclerView].
 */

fun RecyclerView.removeAllItemDecorations() {
    while (itemDecorationCount > 0) {
        removeItemDecorationAt(0)
    }
}

fun WebView.enableJS() = apply { settings.javaScriptEnabled = true }

fun WebView.onPageLoaded(callback: () -> Unit) {
    webViewClient = object : WebViewClient() {
        override fun onPageCommitVisible(view: WebView?, url: String?) {
            callback.invoke()
        }
    }
}

fun MenuItem.respondToQuery(queryHint: String, callback: (String) -> Unit) {
    val searchView = actionView as androidx.appcompat.widget.SearchView
    searchView.queryHint = queryHint

    searchView.setOnQueryTextListener(object :
        androidx.appcompat.widget.SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(p0: String?): Boolean = false
        override fun onQueryTextChange(query: String?): Boolean {
            callback.invoke(query.toNotNull())

            return false
        }
    })
}

fun TextView.alignText() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        justificationMode = LineBreaker.JUSTIFICATION_MODE_INTER_WORD
    }
}

/*
 *  This is a temp solution to include a self sliding viewpager on top of Students/Dept Screen
 *  Will be refactored into a more secure implementation later on, because handler is deprecated.
 */

@BindingAdapter("setScrollableViewPager")
fun setScrollableViewPager(viewPager: ViewPager2, adapter: CarouselAdapter) {
    val sliderHandler = Handler()

    val sliderRunnable = Runnable {
        when (viewPager.currentItem == adapter.itemCount - 1) {
            true -> viewPager.currentItem = 0
            else -> viewPager.currentItem = viewPager.currentItem + 1
        }
    }

    viewPager.setPageTransformer(MarginPageTransformer(30))

    viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            sliderHandler.removeCallbacks(sliderRunnable)
            sliderHandler.postDelayed(sliderRunnable, 2500)
        }

        override fun onPageScrollStateChanged(state: Int) {
            when (state == ViewPager2.SCROLL_STATE_IDLE) {
                true -> sliderHandler.postDelayed(sliderRunnable, 2500)
                else -> Unit
            }
        }
    })
}

fun TabLayout.onTabSelected(callback: (TabLayout.Tab) -> Unit) {
    addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
        override fun onTabSelected(tab: TabLayout.Tab?) {
            tab?.let { callback.invoke(it) }
        }

        override fun onTabUnselected(tab: TabLayout.Tab?) {
            //
        }

        override fun onTabReselected(tab: TabLayout.Tab?) {
            //
        }
    })
}