package com.elmepa.supportv2.di

import com.elmepa.supportv2.repository.SupportRepository
import com.elmepa.supportv2.repository.SupportRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class SupportRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindSupportRepository(impl: SupportRepositoryImpl): SupportRepository
}
