package com.elmepa.personnel.remote.repository

import com.elmepa.personnel.model.Person
import com.elmepa.personnel.remote.mapper.PersonnelMapper
import com.elmepa.personnel.remote.model.PersonnelDto
import com.elmepa.personnel.repository.PersonnelRepository
import com.google.firebase.firestore.FirebaseFirestore
import com.stathis.data.util.FULLNAME
import com.stathis.data.util.PERSONNEL_DB_PATH
import com.stathis.domain.model.DomainResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

internal class PersonnelRepositoryImpl @Inject constructor(
    private val fireStore: FirebaseFirestore
) : PersonnelRepository {

    override fun fetchAllPersonnel(): Flow<DomainResult<List<Person>>> = flow {
        val result = fireStore.collection(PERSONNEL_DB_PATH)
            .orderBy(FULLNAME)
            .get()
            .await()
            .toObjects(PersonnelDto::class.java)

        val personnel = PersonnelMapper.toDomainModel(result)
        emit(DomainResult.Success(personnel))
    }

    override fun searchPersonnelByName(name: String): Flow<DomainResult<List<Person>>> = flow {
        emit(DomainResult.Success(listOf()))
    }
}
