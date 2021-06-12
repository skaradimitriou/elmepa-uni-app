package com.stathis.elmepaunivapp.database

import androidx.room.*
import com.stathis.elmepaunivapp.ui.announcements.model.Announcement
import java.util.*

@Dao
interface AnnouncementsDaoKt {

    @Query("SELECT * FROM Announcements")
    suspend fun getAll(): List<Announcement>

    @Query("DELETE from Announcements")
    suspend fun deleteAll()

    @Insert
    suspend fun insert(announcement: Announcement)

    @Insert
    suspend fun insertAll(announcement: ArrayList<Announcement>)

    @Delete
    suspend fun update(announcement: ArrayList<Announcement>)

    @Delete
    suspend fun delete(announcement: Announcement)
}