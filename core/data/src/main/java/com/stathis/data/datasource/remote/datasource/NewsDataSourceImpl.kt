package com.stathis.data.datasource.remote.datasource

import com.stathis.data.datasource.remote.mapper.news.AnnouncementsMapper
import com.stathis.data.datasource.remote.mapper.news.EventsMapper
import com.stathis.data.datasource.remote.model.announcements.AnnouncementDto
import com.stathis.data.datasource.remote.model.announcements.EventDto
import com.stathis.data.util.ARTICLE
import com.stathis.data.util.EVENTS_URL
import com.stathis.data.util.IMG_HTML_TAG
import com.stathis.data.util.IMG_SOURCE
import com.stathis.data.util.IMG_TYPE
import com.stathis.data.util.NEWS_URL
import com.stathis.data.util.PARAGRAPH_HTML_TAG
import com.stathis.data.util.SPAN
import com.stathis.data.util.TITLE_HTML_TAG
import com.stathis.data.util.TITLE_TYPE
import com.stathis.data.util.URL_ATTR
import com.stathis.data.util.URL_HTML_TAG
import com.stathis.data.util.URL_TYPE
import com.stathis.model.announcements.Announcement
import com.stathis.model.announcements.Event
import org.jsoup.Jsoup
import timber.log.Timber

class NewsDataSourceImpl : NewsDataSource {

    override suspend fun fetchAnnouncementFromRemote(page: Int): List<Announcement> {
        val url = "${NEWS_URL}page/$page/"
        Timber.tag("paging").i("request to => $url")
        val dtoResponse = try {
            Jsoup.connect(url).get().select(ARTICLE).map { article ->
                val imageUrl = article.select(IMG_HTML_TAG).select(IMG_TYPE).attr(IMG_SOURCE)
                val title = article.select(TITLE_HTML_TAG).select(TITLE_TYPE).text()
                val pubDate = article.select(PARAGRAPH_HTML_TAG).select(SPAN).text()
                val openUrl = article.select(URL_HTML_TAG).select(URL_TYPE).attr(URL_ATTR)
                AnnouncementDto(title, imageUrl, openUrl, pubDate)
            }
        } catch (e: Exception) {
            listOf()
        }

        return AnnouncementsMapper.toDomainModel(dtoResponse)
    }

    override suspend fun fetchEventsFromRemote(page: Int): List<Event> {
        val url = "${EVENTS_URL}page/$page/"
        Timber.tag("paging").i("request to => $url")
        val dtoResponse = try {
            Jsoup.connect(url).get().select(ARTICLE).map { article ->
                val imageUrl = article.select(IMG_HTML_TAG).select(IMG_TYPE).attr(IMG_SOURCE)
                val title = article.select(TITLE_HTML_TAG).select(TITLE_TYPE).text()
                val pubDate = article.select(PARAGRAPH_HTML_TAG).select(SPAN).text()
                val openUrl = article.select(URL_HTML_TAG).select(URL_TYPE).attr(URL_ATTR)
                EventDto(title, imageUrl, openUrl, pubDate)
            }
        } catch (e: Exception) {
            listOf()
        }

        return EventsMapper.toDomainModel(dtoResponse)
    }
}