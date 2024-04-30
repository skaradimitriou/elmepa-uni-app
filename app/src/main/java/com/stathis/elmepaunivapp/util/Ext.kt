package com.stathis.elmepaunivapp.util

import android.app.Application
import android.graphics.text.LineBreaker
import android.os.Build
import android.text.Html
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.appcompat.app.ActionBar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException

fun String.toNonHtmlText(): String {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY).toString()
    } else {
        Html.fromHtml(this).toString()
    }
}

inline fun <reified T> Application.readJsonData(fileName: String, data: (T?) -> Unit) {
    try {
        val json = this.assets.open(fileName).bufferedReader().use { it.readText() }
        val type = object : TypeToken<T>() {}.type
        val response: T = Gson().fromJson(json, type)
        data.invoke(response)
    } catch (ioException: IOException) {
        data.invoke(null)
    }
}

/**
 * @param Object
 *
 * Tries to read a local file and deserialize it into a list of the object passed as parameter.
 */

inline fun <reified T> Application.readLocalJsonList(fileName: String, data: (List<T>?) -> Unit) {
    try {
        val json = this.assets.open(fileName).bufferedReader().use { it.readText() }
        val type = object : TypeToken<List<T>>() {}.type
        val list: List<T> = Gson().fromJson(json, type)
        data.invoke(list)
    } catch (ioException: IOException) {
        data.invoke(listOf())
    }
}

fun MenuItem?.onMenuItemTap(callback: (MenuItem) -> Unit) {
    this?.setOnMenuItemClickListener {
        callback.invoke(it)
        true
    }
}

fun MaterialAlertDialogBuilder.showDialog(title: String, desc: String) {
    this.apply {
        this.setTitle(title)
        this.setMessage(desc)
    }.show()
}

fun DrawerLayout.openOrClose(): Boolean {
    if (this.isOpen) this.closeDrawer(GravityCompat.START)
    else this.openDrawer(GravityCompat.START)
    return true
}

fun DrawerLayout.closeMyDrawer() = this.closeDrawer(GravityCompat.START)

fun ActionBar.setupBar(title: String) {
    this.setDisplayHomeAsUpEnabled(true)
    this.title = title
}

//fun Merlin.Builder.construct(context: Context): Merlin = this.withConnectableCallbacks()
//    .withDisconnectableCallbacks()
//    .withBindableCallbacks()
//    .build(context)

fun View.showOrHide(clicked: Boolean) {
    if (clicked) this.visibility = View.INVISIBLE else this.visibility = View.VISIBLE
}

fun View.setClickability(clicked: Boolean) {
    this.isClickable = !clicked
}

fun Snackbar.withColor(@ColorInt colorInt: Int): Snackbar {
    this.view.setBackgroundColor(colorInt)
    return this
}

fun showSnack(view: View, msg: String) = Snackbar.make(view, msg, Snackbar.LENGTH_LONG).show()