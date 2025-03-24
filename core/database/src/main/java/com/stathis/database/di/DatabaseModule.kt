package com.stathis.database.di

import android.app.Application
import androidx.room.Room
import com.stathis.database.local.news.NewsDatabase
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
    fun provideNewsRoomDatabase(application: Application) = Room.databaseBuilder(
        application.applicationContext,
        NewsDatabase::class.java,
        "announcements_db"
    ).fallbackToDestructiveMigration().build()
}
