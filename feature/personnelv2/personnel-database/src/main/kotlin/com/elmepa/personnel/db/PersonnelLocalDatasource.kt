package com.elmepa.personnel.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [PersonEntity::class],
    version = 1,
    exportSchema = false
)
abstract class PersonnelLocalDatasource : RoomDatabase() {

    abstract fun personnelDao(): PersonnelDao
}
