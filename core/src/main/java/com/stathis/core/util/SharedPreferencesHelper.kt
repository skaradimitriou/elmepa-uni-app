package com.stathis.core.util

import android.content.SharedPreferences
import javax.inject.Inject


class SharedPreferencesHelper @Inject constructor(private val preferences: SharedPreferences) {

    private val PREF_TIME = "Pref time"

    fun saveUpdateTime(time: Long) = preferences.edit().putLong(PREF_TIME, time).apply()

    fun getUpdateTime() = preferences.getLong(PREF_TIME, 0)

    fun getCacheDuration() = preferences.getString("pref_cache_duration", "")
}