package com.stathis.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.stathis.core.base.UiModel
import com.stathis.data.datasource.remote.mapper.ContactMapper
import com.stathis.data.datasource.remote.mapper.DepartmentResponseMapper
import com.stathis.data.datasource.remote.model.ContactItemDto
import com.stathis.data.datasource.remote.model.DepartmentResponseDto
import com.stathis.data.util.CONTACT_DB_PATH
import com.stathis.domain.repository.DepartmentRepository
import com.stathis.model.contact.ContactItem
import com.stathis.model.department.DepartmentPersonnelItem
import com.stathis.model.department.DepartmentProgrammeItem
import com.stathis.model.department.DepartmentSocialItem
import com.stathis.model.department.FieldOfStudyParent
import com.stathis.model.general.carousel.CarouselParent
import com.stathis.model.util.ShimmerGenerator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class DepartmentRepositoryImpl(
    private val fireStore: FirebaseFirestore
) : DepartmentRepository {

    override suspend fun fetchDepartmentInformation(): Flow<List<UiModel>> = flow {
        val list = listOf(
            CarouselParent(ShimmerGenerator.list),
            FieldOfStudyParent(ShimmerGenerator.list),
            DepartmentProgrammeItem(ShimmerGenerator.list),
            DepartmentPersonnelItem(ShimmerGenerator.list),
            DepartmentSocialItem(ShimmerGenerator.list)
        )

        emit(list)

        val queryResult = fireStore.collection("department")
            .document("screen_data")
            .get()
            .await()
            .toObject(DepartmentResponseDto::class.java)

        val data = DepartmentResponseMapper.toDomainModel(queryResult)
        emit(data)
    }

    override suspend fun fetchDepartmentContactDetails(): Flow<List<ContactItem>> = flow {
        val query = fireStore.collection(CONTACT_DB_PATH)
            .get()
            .await()
            .toObjects(ContactItemDto::class.java)

        val mappedData = ContactMapper.toDomainModel(query)
        emit(mappedData)
    }
}