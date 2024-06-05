package com.stathis.database.local.personnel

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.stathis.model.personnel.Person
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonnelDao {

    @Query("SELECT * FROM Personnel")
    fun getAllPersonnel(): Flow<List<Person>>

    @Query("SELECT * FROM Personnel WHERE fullName LIKE :query||'%'")
    fun queryPersonnelByFullname(query: String): Flow<List<Person>>

    @Query("DELETE from Personnel")
    suspend fun deleteAll()

    @Insert
    suspend fun insertAll(people: List<Person>)
}