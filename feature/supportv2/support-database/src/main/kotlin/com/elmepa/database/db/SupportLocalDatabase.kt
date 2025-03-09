package com.elmepa.database.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.elmepa.database.model.ApplicationFormEntity
import com.elmepa.database.model.ContactItemEntity
import com.elmepa.database.model.FaqEntity

@Database(
    entities = [FaqEntity::class, ApplicationFormEntity::class, ContactItemEntity::class],
    version = 3,
    exportSchema = false
)
abstract class SupportLocalDatabase : RoomDatabase() {

    abstract fun faqDao(): FaqDao

    abstract fun applicationFormsDao(): ApplicationFormsDao

    abstract fun contactItemDao(): ContactItemDao
}
