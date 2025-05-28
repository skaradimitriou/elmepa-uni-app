package com.elmepa.news.di

import com.elmepa.news.remote.source.NewsDataSource
import com.elmepa.news.remote.source.NewsDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class DataSourceModule {

    @Provides
    @Singleton
    fun provideNewsDataSource(): NewsDataSource = NewsDataSourceImpl()
}
