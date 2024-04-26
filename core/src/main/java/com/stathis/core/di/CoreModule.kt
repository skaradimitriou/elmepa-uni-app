package com.stathis.core.di

import android.app.Application
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.stathis.core.util.SharedPreferencesHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CoreModule {

    @Provides
    fun provideSharedPreferences(app: Application): SharedPreferences {
        //FIXME: Migrate to DataStore.
        return PreferenceManager.getDefaultSharedPreferences(app.applicationContext)
    }

    @Provides
    @Singleton
    fun providePrefsHelper(prefs: SharedPreferences): SharedPreferencesHelper {
        return SharedPreferencesHelper(prefs)
    }
}