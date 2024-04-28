package com.stathis.data.util

import android.app.Application
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException

/**
 * @param Object
 *
 * Tries to read a local file and deserialize it into a list of the object passed as parameter.
 */

inline fun <reified T> Application.readLocalJsonList(fileName: String, data: (List<T>) -> Unit) {
    try {
        val json = this.assets.open(fileName).bufferedReader().use { it.readText() }
        val type = object : TypeToken<List<T>>() {}.type
        val list: List<T> = Gson().fromJson(json, type)
        data.invoke(list)
    } catch (ioException: IOException) {
        data.invoke(listOf())
    }
}