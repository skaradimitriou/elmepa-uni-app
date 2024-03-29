package com.stathis.elmepaunivapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.stathis.elmepaunivapp.model.Announcement

@Database(entities = [Announcement::class] , version = 2, exportSchema = false)
abstract class AnnouncementsDatabase : RoomDatabase() {

    abstract fun announcementDao(): AnnouncementsDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: AnnouncementsDatabase? = null

        fun getDatabase(context: Context): AnnouncementsDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AnnouncementsDatabase::class.java,
                    "announcements_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}
