package com.stathis.data.di

import android.app.Application
import com.stathis.data.repository.DashboardRepositoryImpl
import com.stathis.domain.repository.DashboardRepository
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
}