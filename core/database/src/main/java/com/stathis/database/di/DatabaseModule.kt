package com.stathis.database.di

import android.app.Application
import androidx.room.Room
import com.stathis.database.local.announcements.AnnouncementsDatabase
import com.stathis.database.local.personnel.PersonnelDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideAnnouncementsDatabase(application: Application) = Room.databaseBuilder(
        application.applicationContext,
        AnnouncementsDatabase::class.java,
        "announcements_db"
    ).fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun providePersonnelDatabase(application: Application) = Room.databaseBuilder(
        application.applicationContext,
        PersonnelDatabase::class.java,
        "personnel_db"
    ).fallbackToDestructiveMigration().build()
}