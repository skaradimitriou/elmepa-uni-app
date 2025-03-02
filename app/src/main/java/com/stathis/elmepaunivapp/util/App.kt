package com.stathis.elmepaunivapp.util

import android.app.Application
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.stathis.elmepaunivapp.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())

        val isCrashLoggingEnabled = !BuildConfig.DEBUG
        FirebaseCrashlytics.getInstance().isCrashlyticsCollectionEnabled = isCrashLoggingEnabled

        Timber.d("Crashlytics enabled: $isCrashLoggingEnabled")
    }
}
