package com.stathis.data.datasource.local.professors

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.stathis.model.professors.Professor
import kotlinx.coroutines.flow.Flow

@Dao
interface ProfessorsDao {

    @Query("SELECT * FROM Professors")
    fun getAllProfessors(): Flow<List<Professor>>

    @Query("SELECT * FROM Professors WHERE fullName LIKE :query||'%'")
    fun queryProfessorsByFullname(query: String): Flow<List<Professor>>

    @Query("DELETE from Professors")
    suspend fun deleteAll()

    @Insert
    suspend fun insertAll(professors: List<Professor>)
}