package com.stathis.data.di

import android.app.Application
import androidx.room.Room
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.stathis.data.datasource.local.AnnouncementsDatabase
import com.stathis.data.util.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.jsoup.Connection
import org.jsoup.Jsoup
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideFirestore() = FirebaseFirestore.getInstance()

    @Provides
    @Singleton
    fun provideFirebaseStorage() = FirebaseStorage.getInstance().reference

    @Provides
    @Singleton
    fun provideJsoup(): Connection = Jsoup.connect(BASE_URL)

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