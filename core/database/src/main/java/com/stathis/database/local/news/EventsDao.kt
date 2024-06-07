package com.stathis.database.local.news

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.stathis.model.announcements.Event

@Dao
interface EventsDao {

    @Query("SELECT * FROM Events")
    fun pagingSource(): PagingSource<Int, Event>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(announcements: List<Event>)

    @Query("DELETE from Events")
    suspend fun deleteAll()
}