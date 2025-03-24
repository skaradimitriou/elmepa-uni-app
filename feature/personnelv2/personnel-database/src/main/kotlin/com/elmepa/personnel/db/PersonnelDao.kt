package com.elmepa.personnel.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonnelDao {

    @Query("SELECT * FROM personnel")
    fun getAllPersonnel(): Flow<List<PersonEntity>>

    @Query("SELECT * FROM personnel WHERE fullName LIKE :fullName||'%'")
    fun getPersonnelByFullName(fullName: String): Flow<List<PersonEntity>>

    @Insert
    suspend fun insertAll(items: List<PersonEntity>)

    @Query("DELETE FROM personnel")
    suspend fun deleteAll()
}
