package com.stathis.datastore.di

import android.app.Application
import com.google.gson.Gson
import com.stathis.datastore.caching.CacheManager
import com.stathis.datastore.caching.CacheManagerImpl
import com.stathis.datastore.datastore.FaqDataStore
import com.stathis.datastore.datastore.FaqDataStoreImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatastoreModule {

    @Provides
    @Singleton
    fun provideFaqDatastore(
        app: Application,
        gson: Gson
    ): FaqDataStore = FaqDataStoreImpl(app, gson)

    @Provides
    @Singleton
    fun provideCacheManager(app: Application): CacheManager = CacheManagerImpl(app)
}
