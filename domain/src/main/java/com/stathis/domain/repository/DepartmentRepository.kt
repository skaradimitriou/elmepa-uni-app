package com.stathis.domain.repository

import com.stathis.model.contact.ContactItem
import kotlinx.coroutines.flow.Flow

interface DepartmentRepository {

    suspend fun fetchDepartmentContactDetails(): Flow<List<ContactItem>>
}