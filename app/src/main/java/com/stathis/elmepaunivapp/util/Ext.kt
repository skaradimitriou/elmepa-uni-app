package com.stathis.elmepaunivapp.util

import android.app.Application
import android.graphics.text.LineBreaker
import android.os.Build
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.tabs.TabLayout
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.stathis.elmepaunivapp.model.professor.Professor
import java.io.IOException

fun TextView.alignText() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        this.justificationMode = LineBreaker.JUSTIFICATION_MODE_INTER_WORD
    }
}

fun SwipeRefreshLayout.stopRefresh() {
    this.isRefreshing = false
}

fun Application.readLocalJson(jsonName: String): String {
    return this.assets.open(jsonName).bufferedReader().use { it.readText() }
}

/**
 * @param Object
 *
 * Tries to read a local file and deserialize it into a list of the object passed as parameter.
 */

inline fun <reified T>Application.readLocalJsonList(fileName: String, data: (List<T>?) -> Unit) {
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

fun DrawerLayout.closeMyDrawer() = this.closeDrawer(GravityCompat.START)

fun ActionBar.setupBar(title: String) {
    this.setDisplayHomeAsUpEnabled(true)
    this.title = title
}