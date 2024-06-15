package com.stathis.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.stathis.data.remote.mapper.FaqMapper
import com.stathis.data.remote.model.FaqDto
import com.stathis.data.util.FAQ_DB_PATH
import com.stathis.data.util.FAQ_ORDER_BY_FIELD
import com.stathis.datastore.datastore.FaqDataStore
import com.stathis.model.UiModel
import com.stathis.model.network.NetworkResult
import com.stathis.model.util.ShimmerGenerator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FaqRepositoryImpl @Inject constructor(
    private val fireStore: FirebaseFirestore,
    private val faqDataStore: FaqDataStore
) : FaqRepository {

    override suspend fun fetchFaqs(): Flow<NetworkResult<List<UiModel>>> = flow {
        emit(NetworkResult.Loading(ShimmerGenerator.list))

        val queryResult = fireStore.collection(FAQ_DB_PATH)
            .orderBy(FAQ_ORDER_BY_FIELD)
            .get()
            .await()
            .toObjects(FaqDto::class.java)

        val result = FaqMapper.toDomainModel(queryResult)

        faqDataStore.cacheFaqs(result)
        faqDataStore.fetchFaqsFromDataStore().collect { dataFromDataStore ->
            emit(NetworkResult.Success(dataFromDataStore))
        }
    }
}