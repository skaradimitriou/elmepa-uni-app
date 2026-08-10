package com.elmepa.students.data.di

import com.elmepa.students.data.repository.StudentsRepositoryImpl
import com.elmepa.students.domain.repository.StudentsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class StudentsRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindSupportRepository(impl: StudentsRepositoryImpl): StudentsRepository
}
