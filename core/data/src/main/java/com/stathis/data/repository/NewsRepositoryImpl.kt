package com.stathis.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.stathis.data.remote.datasource.NewsDataSource
import com.stathis.data.remote.paging.AnnouncementsRemoteMediator
import com.stathis.data.remote.paging.EventsRemoteMediator
import com.stathis.data.util.DIV_CONTENT
import com.stathis.database.local.news.NewsDatabase
import com.stathis.model.network.NetworkResult
import kotlinx.coroutines.flow.flow
import org.jsoup.Jsoup
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class NewsRepositoryImpl @Inject constructor(
    private val localDataSource: NewsDatabase,
    private val remoteDataSource: NewsDataSource
) : NewsRepository {

    override fun fetchAnnouncementFromRemote() = Pager(
        config = PagingConfig(
            pageSize = 15,
            initialLoadSize = 2 * 15
        ),
        initialKey = 1,
        remoteMediator = AnnouncementsRemoteMediator(
            localDb = localDataSource,
            remoteSource = remoteDataSource
        ),
        pagingSourceFactory = {
            localDataSource.announcementDao().pagingSource()
        }
    )

    override fun fetchEventsFromRemote() = Pager(
        config = PagingConfig(
            pageSize = 15,
            initialLoadSize = 2 * 15
        ),
        initialKey = 1,
        remoteMediator = EventsRemoteMediator(
            localDb = localDataSource,
            remoteSource = remoteDataSource
        ),
        pagingSourceFactory = {
            localDataSource.eventsDao().pagingSource()
        }
    )

    override fun fetchPostDetails(urlToScrape: String) = flow {
        try {
            val postDetailsHtmlContentDto = Jsoup.connect(urlToScrape)
                .get()
                .select(DIV_CONTENT)
                .joinToString { divs ->
                    divs.html()
                }.trim()

            emit(NetworkResult.Success(postDetailsHtmlContentDto))
        } catch (e: Exception) {
            emit(NetworkResult.Failure(e.localizedMessage?.toString()))
        }
    }
}
