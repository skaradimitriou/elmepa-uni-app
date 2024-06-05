package com.stathis.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.stathis.data.datasource.remote.mapper.ContactMapper
import com.stathis.data.datasource.remote.mapper.DepartmentResponseMapper
import com.stathis.data.datasource.remote.model.ContactItemDto
import com.stathis.data.datasource.remote.model.DepartmentResponseDto
import com.stathis.data.util.CONTACT_DB_PATH
import com.stathis.data.util.DEPT_DB_PATH
import com.stathis.data.util.SCREEN_DATA
import com.stathis.model.department.DepartmentPersonnelItem
import com.stathis.model.department.DepartmentProgrammeItem
import com.stathis.model.department.DepartmentSocialItem
import com.stathis.model.department.FieldOfStudyParent
import com.stathis.model.general.carousel.CarouselParent
import com.stathis.model.network.NetworkResult
import com.stathis.model.util.ShimmerGenerator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class DepartmentRepositoryImpl(
    private val fireStore: FirebaseFirestore
) : DepartmentRepository {

    override suspend fun fetchDepartmentInformation(): Flow<NetworkResult<List<com.stathis.model.UiModel>>> = flow {
        val list = listOf(
            CarouselParent(ShimmerGenerator.list),
            FieldOfStudyParent(ShimmerGenerator.list),
            DepartmentProgrammeItem(ShimmerGenerator.list),
            DepartmentPersonnelItem(ShimmerGenerator.list),
            DepartmentSocialItem(ShimmerGenerator.list)
        )

        emit(NetworkResult.Loading(list))

        val queryResult = fireStore.collection(DEPT_DB_PATH)
            .document(SCREEN_DATA)
            .get()
            .await()
            .toObject(DepartmentResponseDto::class.java)

        val data = DepartmentResponseMapper.toDomainModel(queryResult)
        emit(NetworkResult.Success(data))
    }

    override suspend fun fetchDepartmentContactDetails(): Flow<NetworkResult<List<com.stathis.model.UiModel>>> = flow {
        val query = fireStore.collection(CONTACT_DB_PATH)
            .get()
            .await()
            .toObjects(ContactItemDto::class.java)

        val mappedData = ContactMapper.toDomainModel(query)
        emit(NetworkResult.Success(mappedData))
    }
}