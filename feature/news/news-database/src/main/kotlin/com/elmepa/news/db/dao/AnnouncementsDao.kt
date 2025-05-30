package com.elmepa.news.db.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.elmepa.news.db.entity.AnnouncementEntity

@Dao
interface AnnouncementsDao {

    @Query("SELECT * FROM announcements")
    fun pagingSource(): PagingSource<Int, AnnouncementEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(announcements: List<AnnouncementEntity>)

    @Query("DELETE from Announcements")
    suspend fun deleteAll()
}
