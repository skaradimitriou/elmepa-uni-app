package com.stathis.core.util

import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import android.webkit.WebView
import android.widget.EditText
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

fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun afterTextChanged(p0: Editable?) {
            afterTextChanged.invoke(p0.toString())
        }
    })
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