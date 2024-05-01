package com.stathis.domain.usecase

import com.stathis.core.base.BaseUseCase
import com.stathis.domain.repository.DepartmentRepository
import com.stathis.model.contact.ContactItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchContactDetailsUseCase @Inject constructor(
    private val repo: DepartmentRepository
) : BaseUseCase<Flow<List<ContactItem>>> {

    override suspend fun invoke(vararg args: Any?) = repo.fetchDepartmentContactDetails()
}