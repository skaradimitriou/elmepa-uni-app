package com.elmepa.personnel.remote.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.stathis.data.remote.mapper.personnel.PersonnelMapper
import com.stathis.data.remote.model.personnel.PersonnelDto
import com.stathis.data.util.FULLNAME
import com.stathis.data.util.PERSONNEL_DB_PATH
import com.stathis.domain.model.DomainResult
import com.stathis.model.UiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

internal class PersonnelRepositoryImpl @Inject constructor(
    private val fireStore: FirebaseFirestore
) : com.elmepa.personnel.repository.PersonnelRepository {

    override fun fetchAllPersonnel(): Flow<DomainResult<List<UiModel>>> = flow {
        val result = fireStore.collection(PERSONNEL_DB_PATH)
            .orderBy(FULLNAME)
            .get()
            .await()
            .toObjects(PersonnelDto::class.java)

        val personnel = PersonnelMapper.toDomainModel(result)
        emit(DomainResult.Success(personnel))
    }

    override fun searchPersonnelByName(name: String): Flow<DomainResult<List<UiModel>>> = flow {
        emit(DomainResult.Success(listOf()))
    }
}
