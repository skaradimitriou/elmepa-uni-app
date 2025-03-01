package com.elmepa.homedata.di

import com.elmepa.homedata.repository.DashboardRepositoryImpl
import com.elmepa.homedomain.repository.DashboardRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class HomeRepositoryModule {

    @Binds
    @Singleton
    internal abstract fun bindDashboardRepository(impl: DashboardRepositoryImpl): DashboardRepository
}
