package com.elmepa.personnel.di

import android.app.Application
import androidx.room.Room
import com.elmepa.personnel.db.PersonnelLocalDatasource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

const val PERSONNEL_DB_NAME = "personnel_db"

@Module
@InstallIn(SingletonComponent::class)
class PersonnelDatabaseModule {

    @Provides
    @Singleton
    fun providePersonnelDatabase(application: Application) = Room.databaseBuilder(
        application.applicationContext,
        PersonnelLocalDatasource::class.java,
        PERSONNEL_DB_NAME
    ).fallbackToDestructiveMigration().build()
}
