package com.stathis.data.datasource.datastore

import kotlinx.coroutines.flow.Flow

interface AnnouncementsDataStore {

    suspend fun saveUpdateTime(time: Long)

    suspend fun getUpdateTime(): Flow<Long>
}