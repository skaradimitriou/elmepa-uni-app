package com.elmepa.departmentv2.data.di

import com.elmepa.departmentv2.data.repository.DepartmentRepositoryImpl
import com.elmepa.departmentv2.domain.repository.DepartmentRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DepartmentRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindDepartmentRepository(impl: DepartmentRepositoryImpl): DepartmentRepository
}
