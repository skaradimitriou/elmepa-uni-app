package com.stathis.elmepaunivapp.util

import android.app.Application
import android.content.Context
import android.graphics.text.LineBreaker
import android.os.Build
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import android.view.View
import android.webkit.WebView
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.novoda.merlin.Merlin
import com.stathis.elmepaunivapp.callbacks.DashboardClickListener
import com.stathis.elmepaunivapp.model.professor.Professor
import com.stathis.elmepaunivapp.ui.home.dashboard.DashboardViewModel
import com.stathis.elmepaunivapp.ui.home.dashboard.model.DashboardOption
import java.io.IOException

fun TextView.alignText() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        this.justificationMode = LineBreaker.JUSTIFICATION_MODE_INTER_WORD
    }
}

fun SwipeRefreshLayout.stopRefresh() {
    this.isRefreshing = false
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

fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun afterTextChanged(p0: Editable?) {
            afterTextChanged.invoke(p0.toString())
        }
    })
}

fun Professor.equalsName(name: String): Boolean {
    return this.fullName.lowercase().contains(name.lowercase())
}

fun List<Professor>.sortedAlphabetically(): List<Professor> {
    return this.sortedWith(compareBy { it.fullName })
}

fun TabLayout.onTabSelected(selectedTab: (Int) -> Unit) {
    this.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
        override fun onTabSelected(tab: TabLayout.Tab?) {
            tab?.position?.let { selectedTab.invoke(it) }
        }

        override fun onTabUnselected(tab: TabLayout.Tab?) {}
        override fun onTabReselected(tab: TabLayout.Tab?) {}
    })
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

fun Merlin.Builder.construct(context: Context): Merlin = this.withConnectableCallbacks()
    .withDisconnectableCallbacks()
    .withBindableCallbacks()
    .build(context)

fun WebView.enableJS() {
    this.settings.javaScriptEnabled = true
}

fun View.showOrHide(clicked : Boolean) {
    if (clicked) this.visibility = View.INVISIBLE else this.visibility = View.VISIBLE
}

fun View.setClickability(clicked : Boolean) {
    this.isClickable = !clicked
}

fun showSnack(view : View, msg : String) = Snackbar.make(view, msg, Snackbar.LENGTH_LONG).show()

fun DashboardViewModel.addCallback(item : (DashboardOption) -> Unit){
    this.addCallback(object : DashboardClickListener{
        override fun dashboardItemClicked(option: DashboardOption) {
            item.invoke(option)
        }
    })
}