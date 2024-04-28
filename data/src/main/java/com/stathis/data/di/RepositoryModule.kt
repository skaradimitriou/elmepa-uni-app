package com.stathis.data.di

import android.app.Application
import com.stathis.core.util.SharedPreferencesHelper
import com.stathis.data.datasource.local.AnnouncementsDao
import com.stathis.data.datasource.remote.services.AnnouncementsRemoteDataSource
import com.stathis.data.repository.AnnouncementsRepositoryImpl
import com.stathis.data.repository.DashboardRepositoryImpl
import com.stathis.data.repository.ResearchRepositoryImpl
import com.stathis.domain.repository.AnnouncementRepository
import com.stathis.domain.repository.DashboardRepository
import com.stathis.domain.repository.ResearchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    fun provideDashboardRepository(app: Application): DashboardRepository {
        return DashboardRepositoryImpl(app)
    }

    @Provides
    fun provideAnnouncementsRepository(
        localDataSource: AnnouncementsDao,
        remoteDataSource: AnnouncementsRemoteDataSource,
        preferencesHelper: SharedPreferencesHelper
    ): AnnouncementRepository {
        return AnnouncementsRepositoryImpl(localDataSource, remoteDataSource, preferencesHelper)
    }

    @Provides
    fun provideResearchRepository(app: Application): ResearchRepository {
        return ResearchRepositoryImpl(app)
    }
}