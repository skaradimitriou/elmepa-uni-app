package com.stathis.data.datasource.local.professors

import androidx.room.Database
import androidx.room.RoomDatabase
import com.stathis.model.professors.Professor

@Database(entities = [Professor::class], version = 3, exportSchema = false)
abstract class ProfessorsDatabase : RoomDatabase() {

    abstract fun professorsDao(): ProfessorsDao
}