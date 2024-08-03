package com.stathis.domain.department

import com.stathis.common.base.BaseUseCase
import com.stathis.common.di.IoDispatcher
import com.stathis.common.util.toNotNull
import com.stathis.data.repository.DepartmentRepository
import com.stathis.model.UiModel
import com.stathis.model.department.DepartmentPersonnelItem
import com.stathis.model.network.NetworkResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.flow.zip
import javax.inject.Inject

class FetchDepartmentInfoUseCase @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val repo: DepartmentRepository,
) : BaseUseCase<Flow<NetworkResult<List<UiModel>>>> {

    private val job = Job()
    private val scope = CoroutineScope(job + dispatcher)

    override suspend fun invoke(vararg args: Any?): Flow<NetworkResult<List<UiModel>>> = flow {
        val deptData = scope.async { repo.fetchDepartmentInformation() }.await()
        val depMembers = scope.async { repo.fetchDepMembers() }.await()

        val data = mutableListOf<UiModel>()

        deptData.zip(depMembers) { deptDataResult, depMembersResult ->
            when (deptDataResult) {
                is NetworkResult.Success -> {
                    data.clear()
                    data.addAll(deptDataResult.data.toNotNull())
                }

                is NetworkResult.Failure -> {
                    emit(NetworkResult.Failure(deptDataResult.errorBody))
                }

                else -> Unit
            }

            when (depMembersResult) {
                is NetworkResult.Success -> {
                    depMembersResult.data?.let { depMembers ->
                        data.add(3, DepartmentPersonnelItem(depMembers))
                    }
                }

                is NetworkResult.Failure -> {
                    emit(NetworkResult.Failure(depMembersResult.errorBody))
                }

                else -> Unit

            }

            if (deptDataResult is NetworkResult.Success && depMembersResult is NetworkResult.Success) {
                emit(NetworkResult.Success(data))
            }
        }.toList()
    }

    fun cancelJob() = job.cancel()
}