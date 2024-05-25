package com.stathis.data.di

import com.stathis.data.datasource.datastore.AnnouncementsDataStore
import com.stathis.data.datasource.local.announcements.AnnouncementsDatabase
import com.stathis.data.datasource.remote.services.AnnouncementsRemoteDataSource
import com.stathis.data.datasource.remote.services.AnnouncementsRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    @Provides
    fun provideAnnouncementsRemoteDataSource(
        announcementsDb: AnnouncementsDatabase,
        dataStore: AnnouncementsDataStore
    ): AnnouncementsRemoteDataSource = AnnouncementsRemoteDataSourceImpl(
        announcementsDb,
        dataStore
    )
}