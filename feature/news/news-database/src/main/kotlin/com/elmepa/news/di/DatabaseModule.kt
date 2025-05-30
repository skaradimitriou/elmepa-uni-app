package com.elmepa.news.di

import android.app.Application
import androidx.room.Room
import com.elmepa.news.db.NewsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private const val DB_NAME = "announcements_db"

@Module
@InstallIn(SingletonComponent::class)
internal class DatabaseModule {

    @Provides
    @Singleton
    fun provideNewsRoomDatabase(application: Application) = Room.databaseBuilder(
        application.applicationContext,
        NewsDatabase::class.java,
        DB_NAME
    ).fallbackToDestructiveMigration(dropAllTables = true).build()
}
