package com.stathis.data.di

import android.app.Application
import com.google.firebase.firestore.FirebaseFirestore
import com.stathis.data.datasource.datastore.AnnouncementsDataStore
import com.stathis.data.datasource.local.announcements.AnnouncementsDatabase
import com.stathis.data.datasource.local.personnel.PersonnelDatabase
import com.stathis.data.datasource.remote.services.AnnouncementsRemoteDataSource
import com.stathis.data.repository.AnnouncementsRepositoryImpl
import com.stathis.data.repository.DashboardRepositoryImpl
import com.stathis.data.repository.DepartmentRepositoryImpl
import com.stathis.data.repository.FaqRepositoryImpl
import com.stathis.data.repository.GeneralAppInfoRepositoryImpl
import com.stathis.data.repository.NetworkRepositoryImpl
import com.stathis.data.repository.PersonnelRepositoryImpl
import com.stathis.data.repository.ResearchRepositoryImpl
import com.stathis.data.repository.StudentsRepositoryImpl
import com.stathis.data.repository.SyllabusRepositoryImpl
import com.stathis.domain.repository.AnnouncementRepository
import com.stathis.domain.repository.DashboardRepository
import com.stathis.domain.repository.DepartmentRepository
import com.stathis.domain.repository.FaqRepository
import com.stathis.domain.repository.GeneralAppInfoRepository
import com.stathis.domain.repository.NetworkRepository
import com.stathis.domain.repository.PersonnelRepository
import com.stathis.domain.repository.ResearchRepository
import com.stathis.domain.repository.StudentsRepository
import com.stathis.domain.repository.SyllabusRepository
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
    fun provideDashboardRepository(app: Application): DashboardRepository {
        return DashboardRepositoryImpl(app)
    }

    @Provides
    @Singleton
    fun provideAnnouncementsRepository(
        announcementDb: AnnouncementsDatabase,
        remoteDataSource: AnnouncementsRemoteDataSource,
        dataStore: AnnouncementsDataStore
    ): AnnouncementRepository = AnnouncementsRepositoryImpl(
        announcementDb,
        remoteDataSource,
        dataStore
    )

    @Provides
    @Singleton
    fun provideResearchRepository(
        fireStore: FirebaseFirestore
    ): ResearchRepository = ResearchRepositoryImpl(fireStore)


    @Provides
    @Singleton
    fun providePersonnelRepository(
        fireStore: FirebaseFirestore,
        localDataSource: PersonnelDatabase
    ): PersonnelRepository = PersonnelRepositoryImpl(fireStore, localDataSource)

    @Provides
    @Singleton
    fun provideSyllabusRepository(
        app: Application,
        fireStore: FirebaseFirestore
    ): SyllabusRepository = SyllabusRepositoryImpl(app, fireStore)

    @Provides
    @Singleton
    fun provideDepartmentRepository(
        firestore: FirebaseFirestore
    ): DepartmentRepository = DepartmentRepositoryImpl(firestore)

    @Provides
    @Singleton
    fun provideStudentsRepository(
        fireStore: FirebaseFirestore
    ): StudentsRepository = StudentsRepositoryImpl(fireStore)

    @Provides
    @Singleton
    fun provideFaqRepository(
        fireStore: FirebaseFirestore
    ): FaqRepository = FaqRepositoryImpl(fireStore)

    @Provides
    @Singleton
    fun provideGeneralRepository(
        app: Application
    ): GeneralAppInfoRepository = GeneralAppInfoRepositoryImpl(app)
}