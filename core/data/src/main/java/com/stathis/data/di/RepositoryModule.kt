package com.stathis.data.di

import android.app.Application
import com.google.firebase.firestore.FirebaseFirestore
import com.stathis.data.remote.datasource.DepartmentDataSource
import com.stathis.data.repository.DepartmentRepository
import com.stathis.data.repository.DepartmentRepositoryImpl
import com.stathis.data.repository.NetworkRepository
import com.stathis.data.repository.NetworkRepositoryImpl
import com.stathis.data.repository.ResearchRepository
import com.stathis.data.repository.ResearchRepositoryImpl
import com.stathis.data.repository.StudentsRepository
import com.stathis.data.repository.StudentsRepositoryImpl
import com.stathis.data.repository.SyllabusRepository
import com.stathis.data.repository.SyllabusRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideNetworkRepository(): NetworkRepository = NetworkRepositoryImpl()

    @Provides
    @Singleton
    fun provideResearchRepository(
        fireStore: FirebaseFirestore
    ): ResearchRepository = ResearchRepositoryImpl(fireStore)

    @Provides
    @Singleton
    fun provideSyllabusRepository(
        app: Application,
        fireStore: FirebaseFirestore
    ): SyllabusRepository = SyllabusRepositoryImpl(app, fireStore)

    @Provides
    @Singleton
    fun provideDepartmentRepository(
        firestore: FirebaseFirestore,
        remoteDataSource: DepartmentDataSource
    ): DepartmentRepository = DepartmentRepositoryImpl(
        fireStore = firestore,
        remoteDataSource = remoteDataSource
    )

    @Provides
    @Singleton
    fun provideStudentsRepository(
        fireStore: FirebaseFirestore
    ): StudentsRepository = StudentsRepositoryImpl(fireStore)
}
