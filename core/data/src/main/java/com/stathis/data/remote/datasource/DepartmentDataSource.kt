package com.stathis.data.remote.datasource

import com.stathis.model.department.DepMember
import com.stathis.model.network.NetworkResult
import kotlinx.coroutines.flow.Flow

interface DepartmentDataSource {

    suspend fun fetchDepMembers(): Flow<NetworkResult<List<DepMember>>>
}