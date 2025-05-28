package com.elmepa.news.db.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.elmepa.news.db.entity.EventEntity

@Dao
interface EventsDao {

    @Query("SELECT * FROM events")
    fun pagingSource(): PagingSource<Int, EventEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(announcements: List<EventEntity>)

    @Query("DELETE from Events")
    suspend fun deleteAll()
}
