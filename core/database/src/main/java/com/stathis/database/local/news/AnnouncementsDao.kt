package com.stathis.database.local.news

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.stathis.model.announcements.Announcement

@Dao
interface AnnouncementsDao {

    @Query("SELECT * FROM Announcements")
    fun pagingSource(): PagingSource<Int, Announcement>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(announcements: List<Announcement>)

    @Query("DELETE from Announcements")
    suspend fun deleteAll()
}