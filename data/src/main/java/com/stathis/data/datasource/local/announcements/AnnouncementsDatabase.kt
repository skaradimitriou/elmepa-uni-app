package com.stathis.data.datasource.local.announcements

import androidx.room.Database
import androidx.room.RoomDatabase
import com.stathis.model.announcements.Announcement

@Database(entities = [Announcement::class], version = 3, exportSchema = false)
abstract class AnnouncementsDatabase : RoomDatabase() {

    abstract fun announcementDao(): AnnouncementsDao
}