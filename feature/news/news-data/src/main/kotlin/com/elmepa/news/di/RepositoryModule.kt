package com.elmepa.news.di

import com.elmepa.news.db.NewsDatabase
import com.elmepa.news.remote.repository.NewsRepositoryImpl
import com.elmepa.news.remote.source.NewsDataSource
import com.elmepa.news.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class RepositoryModule {

    @Provides
    @Singleton
    fun provideAnnouncementsRepository(
        newsLocalDataSource: NewsDatabase,
        newsRemoteDataSource: NewsDataSource
    ): NewsRepository = NewsRepositoryImpl(
        localDataSource = newsLocalDataSource,
        remoteDataSource = newsRemoteDataSource
    )
}
