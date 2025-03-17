package com.elmepa.personnel.di

import com.elmepa.personnel.remote.repository.PersonnelRepositoryImpl
import com.elmepa.personnel.repository.PersonnelRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class PersonnelRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindSupportRepository(impl: PersonnelRepositoryImpl): PersonnelRepository
}
