package com.stathis.data.di

import com.stathis.data.util.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.jsoup.Connection
import org.jsoup.Jsoup
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class JsoupModule {

    @Provides
    @Singleton
    fun provideJsoup(): Connection = Jsoup.connect(BASE_URL)
}