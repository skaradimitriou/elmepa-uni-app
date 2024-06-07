package com.stathis.data.di

import com.stathis.data.datasource.remote.datasource.NewsDataSource
import com.stathis.data.datasource.remote.datasource.NewsDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    @Provides
    @Singleton
    fun provideNewsDataSource(): NewsDataSource = NewsDataSourceImpl()
}