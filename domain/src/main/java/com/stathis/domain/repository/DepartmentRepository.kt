package com.stathis.domain.repository

import com.stathis.core.base.UiModel
import com.stathis.model.contact.ContactItem
import kotlinx.coroutines.flow.Flow

interface DepartmentRepository {

    suspend fun fetchDepartmentInformation(): Flow<List<UiModel>>

    suspend fun fetchDepartmentContactDetails(): Flow<List<ContactItem>>
}