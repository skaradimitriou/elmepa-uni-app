package com.stathis.elmepaunivapp.util

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager


object SharedPreferencesHelper {

    private val PREF_TIME = "Pref time"
    private lateinit var prefs: SharedPreferences

    fun setHelper(context: Context) {
        prefs = PreferenceManager.getDefaultSharedPreferences(context)
    }

    fun saveUpdateTime(time: Long) {
        prefs.edit().putLong(PREF_TIME, time).apply()
    }

    fun getUpdateTime(): Long {
        return prefs.getLong(PREF_TIME, 0)
    }

    fun getCacheDuration(): String? {
        return prefs.getString("pref_cache_duration", "")
    }
}