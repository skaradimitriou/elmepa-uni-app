package com.elmepa.database.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.elmepa.database.model.FaqEntity

@Database(
    entities = [FaqEntity::class],
    version = 1,
    exportSchema = false
)
abstract class SupportLocalDatabase : RoomDatabase() {

    abstract fun faqDao(): FaqDao
}
