package com.elmepa.personnel.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [PersonEntity::class],
    version = 2,
    exportSchema = false
)
abstract class PersonnelDatabase : RoomDatabase() {

    abstract fun personnelDao(): PersonnelDao
}
