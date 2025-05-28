package com.elmepa.news.remote.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.map
import com.elmepa.news.db.NewsDatabase
import com.elmepa.news.mapper.toModel
import com.elmepa.news.remote.paging.AnnouncementsRemoteMediator
import com.elmepa.news.remote.paging.EventsRemoteMediator
import com.elmepa.news.remote.source.NewsDataSource
import com.elmepa.news.repository.NewsRepository
import com.stathis.data.util.DIV_CONTENT
import com.stathis.model.network.NetworkResult
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import org.jsoup.Jsoup
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
internal class NewsRepositoryImpl @Inject constructor(
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
    ).flow.map { pagingData ->
        pagingData.map { entity ->
            entity.toModel()
        }
    }

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
    ).flow.map { pagingData ->
        pagingData.map { entity ->
            entity.toModel()
        }
    }

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
