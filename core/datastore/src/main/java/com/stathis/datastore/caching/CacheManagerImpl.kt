package com.stathis.datastore.caching

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CacheManagerImpl @Inject constructor(
    private val app: Application
) : CacheManager {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "app_cache")

    private val cacheDuration = 5 * 60 * 1000 // 5 minutes in milliseconds

    override suspend fun saveCacheTimestamp(key: String, timestamp: Long) {
        val dataStoreKey = longPreferencesKey(key)
        app.dataStore.edit { preferences ->
            preferences[dataStoreKey] = timestamp
        }
    }

    override suspend fun getCacheTimestamp(key: String): Long {
        val dataStoreKey = longPreferencesKey(key)
        return app.dataStore.data.map { preferences ->
            preferences[dataStoreKey] ?: 0L
        }.first()
    }

    override suspend fun shouldRefresh(key: String): Boolean {
        val lastTimestamp = getCacheTimestamp(key)
        val currentTime = System.currentTimeMillis()
        return (currentTime - lastTimestamp) > cacheDuration
    }
}
