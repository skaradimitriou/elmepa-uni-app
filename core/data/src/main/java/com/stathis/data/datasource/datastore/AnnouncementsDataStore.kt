package com.stathis.data.datasource.datastore

import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.flow.Flow

interface AnnouncementsDataStore {

    suspend fun saveUpdateTime(key: Preferences.Key<Long>, time: Long)

    suspend fun getUpdateTime(key: Preferences.Key<Long>): Flow<Long>
}