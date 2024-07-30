package com.stathis.datastore.datastore

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.stathis.common.util.toNotNull
import com.stathis.model.support.Faq
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FaqDataStoreImpl @Inject constructor(
    private val app: Application,
    private val gson: Gson
) : FaqDataStore {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "FAQs")

    private val key = stringPreferencesKey("faq_list")

    override suspend fun cacheFaqs(list: List<Faq>) {
        app.dataStore.edit { prefs ->
            val json = gson.toJson(list)
            prefs[key] = json
        }
    }

    override suspend fun fetchFaqsFromDataStore(): Flow<List<Faq>> =
        app.dataStore.data.map { prefs ->
            val json = prefs[key].toNotNull()
            gson.fromJson(json, object : TypeToken<List<Faq>>() {}.type)
        }
}