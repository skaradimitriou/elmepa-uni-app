package com.elmepa.database.di

import android.app.Application
import androidx.room.Room
import com.elmepa.database.db.SupportLocalDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

const val SUPPORT_DB_NAME = "support_db"

@Module
@InstallIn(SingletonComponent::class)
internal class SupportDatabaseModule {

    @Provides
    @Singleton
    fun provideSupportDatabase(application: Application) = Room.databaseBuilder(
        application.applicationContext,
        SupportLocalDatabase::class.java,
        SUPPORT_DB_NAME
    ).fallbackToDestructiveMigration().build()
}
