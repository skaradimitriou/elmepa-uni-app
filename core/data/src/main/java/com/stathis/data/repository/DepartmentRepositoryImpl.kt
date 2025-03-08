package com.stathis.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.stathis.data.remote.datasource.DepartmentDataSource
import com.stathis.data.remote.mapper.ContactMapper
import com.stathis.data.remote.mapper.DepartmentResponseMapper
import com.stathis.data.remote.model.ContactItemDto
import com.stathis.data.remote.model.DepartmentResponseDto
import com.stathis.data.util.CONTACT_DB_PATH
import com.stathis.data.util.DEPT_DB_PATH
import com.stathis.data.util.SCREEN_DATA
import com.stathis.data.util.getAndMapResponse
import com.stathis.model.UiModel
import com.stathis.model.network.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DepartmentRepositoryImpl @Inject constructor(
    private val fireStore: FirebaseFirestore,
    private val remoteDataSource: DepartmentDataSource
) : DepartmentRepository {

    override suspend fun fetchDepartmentInformation(): Flow<NetworkResult<List<UiModel>>> =
        flow {
            val data = getAndMapResponse<DepartmentResponseDto, List<UiModel>>(
                query = fireStore.collection(DEPT_DB_PATH).document(SCREEN_DATA),
                mapInto = { dtoModel ->
                    DepartmentResponseMapper.toDomainModel(dtoModel)
                }
            )


            emit(NetworkResult.Success(data))
        }

    override suspend fun fetchDepartmentContactDetails(): Flow<NetworkResult<List<UiModel>>> =
        flow {
            val data = getAndMapResponse<ContactItemDto, List<UiModel>>(
                query = fireStore.collection(CONTACT_DB_PATH),
                mapInto = { dtoModel -> ContactMapper.toDomainModel(dtoModel) }
            )

            emit(NetworkResult.Success(data))
        }

    override suspend fun fetchDepMembers() = remoteDataSource.fetchDepMembers()
}
