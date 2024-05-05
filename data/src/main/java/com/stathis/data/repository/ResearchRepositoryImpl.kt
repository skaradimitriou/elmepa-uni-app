package com.stathis.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.stathis.data.datasource.remote.mapper.ResearchMapper
import com.stathis.data.datasource.remote.model.ResearchResponseDto
import com.stathis.data.util.RESEARCH_DB_PATH
import com.stathis.domain.repository.ResearchRepository
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ResearchRepositoryImpl @Inject constructor(
    private val fireStore: FirebaseFirestore
) : ResearchRepository {

    override suspend fun fetchResearchDetails() = flow {
        val queryResult = fireStore.collection(RESEARCH_DB_PATH)
            .get()
            .await()
            .toObjects(ResearchResponseDto::class.java)


        val mappedResult = ResearchMapper.toDomainModel(queryResult)
        emit(mappedResult)
    }
}