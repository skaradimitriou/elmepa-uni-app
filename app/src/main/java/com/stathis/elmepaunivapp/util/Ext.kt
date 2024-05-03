package com.stathis.elmepaunivapp.util

import android.app.Application
import android.view.View
import androidx.appcompat.app.ActionBar
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException

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

fun ActionBar.setupBar(title: String) {
    this.setDisplayHomeAsUpEnabled(true)
    this.title = title
}

fun View.showOrHide(clicked: Boolean) {
    if (clicked) this.visibility = View.INVISIBLE else this.visibility = View.VISIBLE
}

fun View.setClickability(clicked: Boolean) {
    this.isClickable = !clicked
}

fun showSnack(view: View, msg: String) = Snackbar.make(view, msg, Snackbar.LENGTH_LONG).show()