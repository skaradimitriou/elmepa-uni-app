package com.elmepa.news.remote.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.elmepa.news.db.NewsDatabase
import com.elmepa.news.db.entity.EventEntity
import com.elmepa.news.mapper.toEntity
import com.elmepa.news.remote.source.NewsDataSource

@OptIn(ExperimentalPagingApi::class)
class EventsRemoteMediator(
    private val localDb: NewsDatabase,
    private val remoteSource: NewsDataSource
) : RemoteMediator<Int, EventEntity>() {

    private var currentPageNo: Int = 0

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, EventEntity>
    ): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> {
                    currentPageNo = 0
                    currentPageNo
                }

                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)

                LoadType.APPEND -> {
                    currentPageNo++
                    currentPageNo
                }
            }


            localDb.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    localDb.eventsDao().deleteAll()
                } else {
                    val result = remoteSource.fetchEventsFromRemote(page = loadKey)
                    val entities = result.map { it.toEntity() }
                    localDb.eventsDao().insertAll(entities)
                }
            }

            MediatorResult.Success(endOfPaginationReached = currentPageNo > 10)
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }
}
