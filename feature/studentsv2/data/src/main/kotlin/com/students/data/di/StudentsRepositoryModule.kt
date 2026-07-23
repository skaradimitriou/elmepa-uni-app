package com.students.data.di

import com.students.data.remote.repository.StudentsRepositoryImpl
import com.students.domain.repository.StudentsRepository
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
