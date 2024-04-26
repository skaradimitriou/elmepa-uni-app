package com.stathis.data.di

import android.app.Application
import androidx.room.Room
import com.stathis.data.datasource.local.AnnouncementsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomDatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(application: Application): AnnouncementsDatabase = Room.databaseBuilder(
        application.applicationContext,
        AnnouncementsDatabase::class.java,
        "announcements_database"
    ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideDao(database: AnnouncementsDatabase) = database.announcementDao()
}