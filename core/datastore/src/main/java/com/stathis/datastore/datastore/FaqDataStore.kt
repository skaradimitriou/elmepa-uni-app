package com.stathis.datastore.datastore

import com.stathis.model.faq.Faq
import kotlinx.coroutines.flow.Flow

interface FaqDataStore {

    suspend fun cacheFaqs(list: List<Faq>)

    suspend fun fetchFaqsFromDataStore(): Flow<List<Faq>>
}