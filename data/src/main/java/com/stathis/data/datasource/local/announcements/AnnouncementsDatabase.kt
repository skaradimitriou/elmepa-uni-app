package com.stathis.data.datasource.local.announcements

import androidx.room.Database
import androidx.room.RoomDatabase
import com.stathis.model.announcements.Announcement
import com.stathis.model.announcements.Event

@Database(entities = [Announcement::class, Event::class], version = 4, exportSchema = false)
abstract class AnnouncementsDatabase : RoomDatabase() {

    abstract fun announcementDao(): AnnouncementsDao

    abstract fun eventsDao(): EventsDao
}