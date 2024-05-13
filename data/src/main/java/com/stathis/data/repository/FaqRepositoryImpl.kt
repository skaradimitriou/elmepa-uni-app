package com.stathis.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.stathis.core.base.UiModel
import com.stathis.data.datasource.remote.mapper.FaqMapper
import com.stathis.data.datasource.remote.model.FaqDto
import com.stathis.data.util.FAQ_DB_PATH
import com.stathis.data.util.FAQ_ORDER_BY_FIELD
import com.stathis.domain.repository.FaqRepository
import com.stathis.model.util.ShimmerGenerator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FaqRepositoryImpl @Inject constructor(
    private val fireStore: FirebaseFirestore
) : FaqRepository {

    override suspend fun fetchFaqs(): Flow<List<UiModel>> = flow {
        emit(ShimmerGenerator.list)

        val queryResult = fireStore.collection(FAQ_DB_PATH)
            .orderBy(FAQ_ORDER_BY_FIELD)
            .get()
            .await()
            .toObjects(FaqDto::class.java)

        val result = FaqMapper.toDomainModel(queryResult)
        emit(result)
    }
}