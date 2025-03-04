package com.elmepa.database.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.elmepa.database.model.ApplicationFormEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ApplicationFormsDao {

    @Query("SELECT * FROM application_forms")
    fun getAllApplicationForms(): Flow<List<ApplicationFormEntity>>

    @Query("DELETE from application_forms")
    suspend fun deleteAll()

    @Insert
    suspend fun insertAll(forms: List<ApplicationFormEntity>)
}
