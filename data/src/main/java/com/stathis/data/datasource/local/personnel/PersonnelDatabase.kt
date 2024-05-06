package com.stathis.data.datasource.local.personnel

import androidx.room.Database
import androidx.room.RoomDatabase
import com.stathis.model.personnel.Person

@Database(entities = [Person::class], version = 3, exportSchema = false)
abstract class PersonnelDatabase : RoomDatabase() {

    abstract fun personnelDao(): PersonnelDao
}