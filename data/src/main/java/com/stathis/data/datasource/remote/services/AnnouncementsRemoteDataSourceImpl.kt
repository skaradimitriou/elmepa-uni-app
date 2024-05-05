package com.stathis.data.datasource.remote.services

import com.stathis.core.util.SharedPreferencesHelper
import com.stathis.data.datasource.local.announcements.AnnouncementsDao
import com.stathis.data.util.DATA_TYPE
import com.stathis.data.util.IMG_HTML_TAG
import com.stathis.data.util.IMG_SOURCE
import com.stathis.data.util.IMG_TYPE
import com.stathis.data.util.PARAGRAPH_HTML_TAG
import com.stathis.data.util.PARAGRAPH_TYPE
import com.stathis.data.util.TITLE_HTML_TAG
import com.stathis.data.util.TITLE_TYPE
import com.stathis.data.util.URL_ATTR
import com.stathis.data.util.URL_HTML_TAG
import com.stathis.data.util.URL_TYPE
import com.stathis.model.announcements.Announcement
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.jsoup.Connection

class AnnouncementsRemoteDataSourceImpl(
    private val jsoup: Connection,
    private val localDataSource: AnnouncementsDao,
    private val preferences: SharedPreferencesHelper
) : AnnouncementsRemoteDataSource {

    override suspend fun fetchAnnouncementFromRemote(): Flow<List<Announcement>> = flow {
        val announcementList = arrayListOf<Announcement>()

        try {
            val doc = jsoup.get().select(DATA_TYPE)
            repeat(12) { i ->
                val imgUrl = doc.select(IMG_HTML_TAG).select(IMG_TYPE).eq(i).attr(IMG_SOURCE)
                val title = doc.select(TITLE_HTML_TAG).select(TITLE_TYPE).eq(i).text()
                val pubDate = doc.select(PARAGRAPH_HTML_TAG).select(PARAGRAPH_TYPE).eq(i).text()
                val detailUrl = doc.select(URL_HTML_TAG).select(URL_TYPE).eq(i).attr(URL_ATTR)
                announcementList.add(Announcement(title, detailUrl, pubDate, imgUrl))
            }

            localDataSource.deleteAll()
            localDataSource.insertAll(announcementList)

            preferences.saveUpdateTime(System.nanoTime())
        } catch (e: Exception) {
            emit(listOf())
        }

        localDataSource.getAll().collect {
            emit(announcementList)
        }
    }
}