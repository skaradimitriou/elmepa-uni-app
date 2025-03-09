package com.elmepa.database.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.elmepa.database.model.ContactItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactItemDao {

    @Query("SELECT * FROM contact_items")
    fun getAllContactItems(): Flow<List<ContactItemEntity>>

    @Insert
    suspend fun insertAll(items: List<ContactItemEntity>)

    @Query("DELETE FROM contact_items")
    suspend fun deleteAll()
}
