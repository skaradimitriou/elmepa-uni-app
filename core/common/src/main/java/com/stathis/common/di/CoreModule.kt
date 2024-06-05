package com.stathis.common.di

import android.app.Application
import com.stathis.common.util.networkmanager.NetworkManager
import com.stathis.common.util.networkmanager.NetworkManagerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CoreModule {

    @Provides
    @Singleton
    fun provideNetworkManager(
        app: Application
    ): NetworkManager = NetworkManagerImpl(app)
}