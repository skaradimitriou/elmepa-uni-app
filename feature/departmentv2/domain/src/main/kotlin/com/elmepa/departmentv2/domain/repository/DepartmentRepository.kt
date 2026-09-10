package com.elmepa.departmentv2.domain.repository

import com.elmepa.departmentv2.domain.model.DepartmentResponse
import com.stathis.domain.model.DomainResult

interface DepartmentRepository {

    suspend fun getDepartmentScreenInfo(): DomainResult<DepartmentResponse>
}
