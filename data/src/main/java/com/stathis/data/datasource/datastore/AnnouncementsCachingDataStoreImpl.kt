package com.stathis.data.datasource.datastore

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.stathis.core.util.toNotNull
import com.stathis.data.util.SETTINGS
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AnnouncementsCachingDataStoreImpl @Inject constructor(
    private val app: Application
) : AnnouncementsDataStore {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = SETTINGS)

    override suspend fun saveUpdateTime(key: Preferences.Key<Long>, time: Long) {
        app.dataStore.edit { prefs ->
            prefs[key] = time
        }
    }

    override suspend fun getUpdateTime(key: Preferences.Key<Long>) =
        app.dataStore.data.map { prefs ->
            prefs[key].toNotNull()
        }
}