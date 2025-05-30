package com.elmepa.news.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.elmepa.news.db.dao.AnnouncementsDao
import com.elmepa.news.db.dao.EventsDao
import com.elmepa.news.db.entity.AnnouncementEntity
import com.elmepa.news.db.entity.EventEntity

@Database(entities = [AnnouncementEntity::class, EventEntity::class], version = 1, exportSchema = false)
abstract class NewsDatabase : RoomDatabase() {

    abstract fun announcementDao(): AnnouncementsDao

    abstract fun eventsDao(): EventsDao
}
