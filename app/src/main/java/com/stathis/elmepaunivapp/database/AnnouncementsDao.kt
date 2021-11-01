package com.stathis.elmepaunivapp.database

import androidx.room.*
import com.stathis.elmepaunivapp.ui.announcements.model.Announcement
import org.jetbrains.annotations.NotNull
import java.util.*

@Dao
interface AnnouncementsDao {

    @Query("SELECT * FROM Announcements")
    fun getAll(): List<Announcement>

    @Query("DELETE from Announcements")
    fun deleteAll()

    @Insert
    fun insert(announcement: Announcement)

    @Insert
    fun insertAll(announcement: ArrayList<Announcement>)

    @Delete
    fun update(announcement: ArrayList<Announcement>)

    @Delete
    fun delete(announcement: Announcement)
}