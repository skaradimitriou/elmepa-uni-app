package com.stathis.data.datasource.remote.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.stathis.data.datasource.remote.datasource.NewsDataSource
import com.stathis.database.local.news.NewsDatabase
import com.stathis.model.announcements.Announcement

@OptIn(ExperimentalPagingApi::class)
class AnnouncementsRemoteMediator(
    private val localDb: NewsDatabase,
    private val remoteSource: NewsDataSource
) : RemoteMediator<Int, Announcement>() {

    private var currentPageNo: Int = 0

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Announcement>
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
                    localDb.announcementDao().deleteAll()
                } else {
                    val result = remoteSource.fetchAnnouncementFromRemote(page = loadKey)
                    localDb.announcementDao().insertAll(result)
                }
            }

            MediatorResult.Success(endOfPaginationReached = currentPageNo > 10)
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }
}