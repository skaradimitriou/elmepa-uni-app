package com.stathis.data.repository

import android.app.Application
import com.stathis.common.R
import com.stathis.model.about.AboutAppCard
import com.stathis.model.about.AboutAppHeader
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GeneralAppInfoRepositoryImpl(
    private val app: Application
) : GeneralAppInfoRepository {

    override suspend fun fetchAboutAppInfo(): Flow<List<com.stathis.model.UiModel>> = flow {
        val list = listOf(
            AboutAppHeader(
                header = app.getString(R.string.about_app_data),
                title = app.getString(R.string.news_publisher_header),
                description = app.getString(R.string.news_publisher_desc)
            ),
            AboutAppCard(
                date = app.getString(R.string.new_version_date),
                description = app.getString(R.string.new_version_desc),
            ),
            AboutAppCard(
                date = app.getString(R.string.third_version_date),
                description = app.getString(R.string.third_version_desc)
            ),
            AboutAppCard(
                date = app.getString(R.string.sec_version_date),
                description = app.getString(R.string.sec_version_desc)
            ),
            AboutAppCard(
                date = app.getString(R.string.first_version_date),
                description = app.getString(R.string.first_version_desc)
            )
        )

        emit(list)
    }
}