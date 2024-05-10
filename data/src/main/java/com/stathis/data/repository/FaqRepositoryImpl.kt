package com.stathis.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.stathis.core.base.UiModel
import com.stathis.data.datasource.remote.mapper.FaqMapper
import com.stathis.data.datasource.remote.model.FaqDto
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

        val queryResult = fireStore.collection("faqs")
            .get()
            .await()
            .toObjects(FaqDto::class.java)

        val result = FaqMapper.toDomainModel(queryResult)
        emit(result)
    }
}