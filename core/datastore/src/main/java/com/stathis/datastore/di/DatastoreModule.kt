package com.stathis.datastore.di

import android.app.Application
import com.stathis.datastore.caching.CacheManager
import com.stathis.datastore.caching.CacheManagerImpl
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
    fun provideCacheManager(app: Application): CacheManager = CacheManagerImpl(app)
}
