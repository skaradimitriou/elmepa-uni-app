package com.stathis.core.util

import android.graphics.text.LineBreaker
import android.os.Build
import android.view.MenuItem
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.TextView
import androidx.databinding.BindingAdapter
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

@BindingAdapter("setHtmlText")
fun TextView.setText(text: String) {
    this.text = text.toNonHtmlText()
}

@BindingAdapter("setRibbonColor")
fun View.setRibbonColor(isMandatory: Boolean) {
    if (isMandatory) {
        setBackgroundResource(com.stathis.core.R.color.lesson_blue)
    } else {
        setBackgroundResource(com.stathis.core.R.color.lesson_orange)
    }
}