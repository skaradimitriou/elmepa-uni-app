package com.stathis.database.local.announcements

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.stathis.model.announcements.Event
import kotlinx.coroutines.flow.Flow

@Dao
interface EventsDao {

    @Query("SELECT * FROM Events")
    fun getAll(): Flow<List<Event>>

    @Query("DELETE from Events")
    suspend fun deleteAll()

    @Insert
    suspend fun insert(announcement: Event)

    @Insert
    suspend fun insertAll(announcement: List<Event>)

    @Update
    suspend fun updateAll(announcement: List<Event>)

    @Delete
    suspend fun delete(announcement: Event)
}