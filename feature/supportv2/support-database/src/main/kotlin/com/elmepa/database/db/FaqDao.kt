package com.elmepa.database.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.elmepa.database.model.FaqEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FaqDao {

    @Query("SELECT * FROM Faqs")
    fun getAllFaqs(): Flow<List<FaqEntity>>

    @Query("DELETE from Faqs")
    suspend fun deleteAll()

    @Insert
    suspend fun insertAll(faqs: List<FaqEntity>)
}
