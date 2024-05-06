package com.stathis.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.stathis.core.base.UiModel
import com.stathis.data.datasource.remote.mapper.StudentsMapper
import com.stathis.data.datasource.remote.model.StudentsResponseDto
import com.stathis.data.util.STUDENTS_DB_PATH
import com.stathis.domain.repository.StudentsRepository
import com.stathis.model.general.carousel.CarouselParent
import com.stathis.model.network.NetworkResult
import com.stathis.model.students.LinkParent
import com.stathis.model.util.ShimmerGenerator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class StudentsRepositoryImpl @Inject constructor(
    private val fireStore: FirebaseFirestore
) : StudentsRepository {

    override suspend fun fetchStudentScreenData(): Flow<NetworkResult<List<UiModel>>> = flow {
        val loadingData = listOf(
            CarouselParent(ShimmerGenerator.list),
            LinkParent(ShimmerGenerator.list)
        )
        emit(NetworkResult.Loading(data = loadingData))

        val queryResult = fireStore.collection(STUDENTS_DB_PATH)
            .document("screen_data")
            .get()
            .await()
            .toObject(StudentsResponseDto::class.java)

        val result = StudentsMapper.toDomainModel(queryResult)
        emit(NetworkResult.Success(result))
    }
}