package com.stathis.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.stathis.data.datasource.remote.mapper.ContactMapper
import com.stathis.data.datasource.remote.model.ContactItemDto
import com.stathis.data.util.CONTACT_DB_PATH
import com.stathis.domain.repository.DepartmentRepository
import com.stathis.model.contact.ContactItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class DepartmentRepositoryImpl(
    private val firestore: FirebaseFirestore
) : DepartmentRepository {

    override suspend fun fetchDepartmentContactDetails(): Flow<List<ContactItem>> = flow {
        val query = firestore.collection(CONTACT_DB_PATH)
            .get()
            .await()
            .toObjects(ContactItemDto::class.java)

        val mappedData = ContactMapper.toDomainModel(query)
        emit(mappedData)
    }
}