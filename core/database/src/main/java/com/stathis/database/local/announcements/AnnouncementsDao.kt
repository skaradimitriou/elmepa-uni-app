package com.stathis.database.local.announcements

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.stathis.model.announcements.Announcement
import kotlinx.coroutines.flow.Flow

@Dao
interface AnnouncementsDao {

    @Query("SELECT * FROM Announcements")
    fun getAll(): Flow<List<Announcement>>

    @Query("DELETE from Announcements")
    suspend fun deleteAll()

    @Insert
    suspend fun insert(announcement: Announcement)

    @Insert
    suspend fun insertAll(announcement: List<Announcement>)

    @Update
    suspend fun updateAll(announcement: List<Announcement>)

    @Delete
    suspend fun delete(announcement: Announcement)
}