package com.stathis.data.datasource.remote.services

import com.stathis.data.datasource.datastore.AnnouncementsDataStore
import com.stathis.data.datasource.remote.mapper.announcements.AnnouncementsMapper
import com.stathis.data.datasource.remote.mapper.announcements.EventsMapper
import com.stathis.data.datasource.remote.mapper.announcements.PostDetailsMapper
import com.stathis.data.datasource.remote.model.announcements.AnnouncementDto
import com.stathis.data.datasource.remote.model.announcements.EventDto
import com.stathis.data.datasource.remote.model.announcements.PostDetailsResponseDto
import com.stathis.data.util.ANNOUNCEMENTS_DS_KEY
import com.stathis.data.util.ARTICLE
import com.stathis.data.util.DIV_CONTENT
import com.stathis.data.util.EVENTS_DS_KEY
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
import com.stathis.database.local.announcements.AnnouncementsDatabase
import com.stathis.model.announcements.details.PostDetailsRequest
import com.stathis.model.announcements.details.PostDetailsResponse
import com.stathis.model.network.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.jsoup.Jsoup

class AnnouncementsRemoteDataSourceImpl(
    announcementsDb: AnnouncementsDatabase,
    private val dataStore: AnnouncementsDataStore
) : AnnouncementsRemoteDataSource {

    private val announcementsLocalDataSource = announcementsDb.announcementDao()
    private val eventsLocalDataSource = announcementsDb.eventsDao()

    override suspend fun fetchAnnouncementFromRemote(): Flow<NetworkResult<List<com.stathis.model.UiModel>>> =
        flow {
            try {
                val response = Jsoup.connect(NEWS_URL).get().select(ARTICLE).map { article ->
                    val imageUrl = article.select(IMG_HTML_TAG).select(IMG_TYPE).attr(IMG_SOURCE)
                    val title = article.select(TITLE_HTML_TAG).select(TITLE_TYPE).text()
                    val pubDate = article.select(PARAGRAPH_HTML_TAG).select(SPAN).text()
                    val openUrl = article.select(URL_HTML_TAG).select(URL_TYPE).attr(URL_ATTR)
                    AnnouncementDto(title, imageUrl, openUrl, pubDate)
                }

                val mappedData = AnnouncementsMapper.toDomainModel(response)

                announcementsLocalDataSource.deleteAll()
                announcementsLocalDataSource.insertAll(mappedData)

                dataStore.saveUpdateTime(ANNOUNCEMENTS_DS_KEY, System.nanoTime())

                announcementsLocalDataSource.getAll().collect { cachedList ->
                    emit(NetworkResult.Success(cachedList))
                }
            } catch (e: Exception) {
                emit(NetworkResult.Failure(e.localizedMessage))
            }
        }

    override suspend fun fetchEventsFromRemote(): Flow<NetworkResult<List<com.stathis.model.UiModel>>> =
        flow {
            try {
                val response = Jsoup.connect(EVENTS_URL).get().select(ARTICLE).map { article ->
                    val imageUrl = article.select(IMG_HTML_TAG).select(IMG_TYPE).attr(IMG_SOURCE)
                    val title = article.select(TITLE_HTML_TAG).select(TITLE_TYPE).text()
                    val pubDate = article.select(PARAGRAPH_HTML_TAG).select(SPAN).text()
                    val openUrl = article.select(URL_HTML_TAG).select(URL_TYPE).attr(URL_ATTR)
                    EventDto(title, imageUrl, openUrl, pubDate)
                }

                val mappedData = EventsMapper.toDomainModel(response)

                eventsLocalDataSource.deleteAll()
                eventsLocalDataSource.insertAll(mappedData)

                dataStore.saveUpdateTime(EVENTS_DS_KEY, System.nanoTime())

                eventsLocalDataSource.getAll().collect { cachedList ->
                    emit(NetworkResult.Success(cachedList))
                }
            } catch (e: Exception) {
                emit(NetworkResult.Failure(e.localizedMessage?.toString()))
            }
        }

    override suspend fun fetchPostDetails(request: PostDetailsRequest): Flow<NetworkResult<PostDetailsResponse>> =
        flow {
            emit(NetworkResult.Loading())

            try {
                val response = Jsoup.connect(request.scrapeUrl)
                    .get()
                    .select(DIV_CONTENT)
                    .joinToString { divs ->
                        divs.html()
                    }.trim()

                val dtoModel = PostDetailsResponseDto(
                    title = request.title,
                    imageUrl = request.imageUrl,
                    pubDate = request.pubDate,
                    htmlContent = response
                )
                val domainModel = PostDetailsMapper.toDomainModel(dtoModel)
                emit(NetworkResult.Success(domainModel))
            } catch (e: Exception) {
                emit(NetworkResult.Failure(e.localizedMessage?.toString()))
            }
        }
}