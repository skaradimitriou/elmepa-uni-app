package com.stathis.data.repository

import android.app.Application
import com.stathis.domain.repository.GeneralAppInfoRepository
import com.stathis.model.UiModel
import com.stathis.model.about.AboutAppCard
import com.stathis.model.about.AboutAppHeader
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GeneralAppInfoRepositoryImpl(
    private val app: Application
) : GeneralAppInfoRepository {

    //FIXME: Add text for all app releases

    override suspend fun fetchAboutAppInfo(): Flow<List<UiModel>> = flow {
        val list = listOf(
            AboutAppHeader(
                header = app.getString(com.stathis.core.R.string.about_app_data),
                title = app.getString(com.stathis.core.R.string.news_publisher_header),
                description = app.getString(com.stathis.core.R.string.news_publisher_desc)
            ),
            AboutAppCard(
                date = "Ιούνιος 2024",
                description = app.getString(com.stathis.core.R.string.new_version_desc),
                btnTxt = "Press me"
            ), AboutAppCard(
                date = "Ιούνιος 2024",
                description = "Lorem ipsum sit dolor amet.",
                btnTxt = "Press me"
            ), AboutAppCard(
                date = "Ιούνιος 2024",
                description = "Lorem ipsum sit dolor amet.",
                btnTxt = "Press me"
            ), AboutAppCard(
                date = "Ιούνιος 2024",
                description = "Lorem ipsum sit dolor amet.",
                btnTxt = "Press me"
            )
        )

        emit(list)
    }
}