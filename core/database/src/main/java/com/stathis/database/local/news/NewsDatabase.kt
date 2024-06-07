package com.stathis.database.local.news

import androidx.room.Database
import androidx.room.RoomDatabase
import com.stathis.model.announcements.Announcement
import com.stathis.model.announcements.Event

@Database(entities = [Announcement::class, Event::class], version = 1, exportSchema = false)
abstract class NewsDatabase : RoomDatabase() {

    abstract fun announcementDao(): AnnouncementsDao

    abstract fun eventsDao(): EventsDao
}