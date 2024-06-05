package com.stathis.data.di

import android.app.Application
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.stathis.data.datasource.datastore.AnnouncementsCachingDataStoreImpl
import com.stathis.data.datasource.datastore.AnnouncementsDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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
    fun provideDataStore(app: Application): AnnouncementsDataStore {
        return AnnouncementsCachingDataStoreImpl(app)
    }
}