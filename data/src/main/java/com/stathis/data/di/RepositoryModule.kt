package com.stathis.data.di

import android.app.Application
import com.google.firebase.firestore.FirebaseFirestore
import com.stathis.core.util.SharedPreferencesHelper
import com.stathis.data.datasource.local.announcements.AnnouncementsDao
import com.stathis.data.datasource.local.personnel.PersonnelDatabase
import com.stathis.data.datasource.remote.services.AnnouncementsRemoteDataSource
import com.stathis.data.repository.AnnouncementsRepositoryImpl
import com.stathis.data.repository.DashboardRepositoryImpl
import com.stathis.data.repository.DepartmentRepositoryImpl
import com.stathis.data.repository.FaqRepositoryImpl
import com.stathis.data.repository.PersonnelRepositoryImpl
import com.stathis.data.repository.ResearchRepositoryImpl
import com.stathis.data.repository.StudentsRepositoryImpl
import com.stathis.data.repository.SyllabusRepositoryImpl
import com.stathis.domain.repository.AnnouncementRepository
import com.stathis.domain.repository.DashboardRepository
import com.stathis.domain.repository.DepartmentRepository
import com.stathis.domain.repository.FaqRepository
import com.stathis.domain.repository.PersonnelRepository
import com.stathis.domain.repository.ResearchRepository
import com.stathis.domain.repository.StudentsRepository
import com.stathis.domain.repository.SyllabusRepository
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
    fun provideResearchRepository(
        fireStore: FirebaseFirestore
    ): ResearchRepository = ResearchRepositoryImpl(fireStore)


    @Provides
    fun providePersonnelRepository(
        fireStore: FirebaseFirestore,
        localDataSource: PersonnelDatabase
    ): PersonnelRepository = PersonnelRepositoryImpl(fireStore, localDataSource)

    @Provides
    fun provideSyllabusRepository(
        app: Application,
        fireStore: FirebaseFirestore
    ): SyllabusRepository = SyllabusRepositoryImpl(app, fireStore)

    @Provides
    fun provideDepartmentRepository(
        firestore: FirebaseFirestore
    ): DepartmentRepository = DepartmentRepositoryImpl(firestore)

    @Provides
    fun provideStudentsRepository(
        fireStore: FirebaseFirestore
    ): StudentsRepository = StudentsRepositoryImpl(fireStore)

    @Provides
    fun provideFaqRepository(
        fireStore: FirebaseFirestore
    ): FaqRepository = FaqRepositoryImpl(fireStore)
}