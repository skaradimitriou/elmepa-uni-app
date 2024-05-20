package com.stathis.data.di

import android.app.Application
import androidx.room.Room
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.stathis.data.datasource.datastore.AnnouncementsCachingDataStoreImpl
import com.stathis.data.datasource.datastore.AnnouncementsDataStore
import com.stathis.data.datasource.local.announcements.AnnouncementsDatabase
import com.stathis.data.datasource.local.personnel.PersonnelDatabase
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
    fun provideDataStore(app: Application): AnnouncementsDataStore {
        return AnnouncementsCachingDataStoreImpl(app)
    }

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

    @Provides
    fun provideDao(database: AnnouncementsDatabase) = database.announcementDao()
}