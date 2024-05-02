package com.stathis.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.stathis.core.base.UiModel
import com.stathis.core.util.makeFirstCharCapital
import com.stathis.data.datasource.remote.mapper.ProfessorsMapper
import com.stathis.data.datasource.remote.model.ProfessorDto
import com.stathis.data.util.FULLNAME
import com.stathis.data.util.PROFESSOR_DB_PATH
import com.stathis.domain.repository.ProfessorsRepository
import com.stathis.model.professors.Professor
import com.stathis.model.util.ShimmerGenerator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ProfessorsRepositoryImpl @Inject constructor(
    private val fireStore: FirebaseFirestore
) : ProfessorsRepository {

    override suspend fun fetchAllProfessors(): Flow<List<UiModel>> = flow {
        emit(ShimmerGenerator.list)

        val result = fireStore.collection(PROFESSOR_DB_PATH)
            .orderBy(FULLNAME)
            .get()
            .await()
            .toObjects(ProfessorDto::class.java)

        val professors = ProfessorsMapper.toDomainModel(result)
        emit(professors)
    }

    override suspend fun searchForProfessor(name: String): Flow<List<Professor>> = flow {
        val result = fireStore.collection(PROFESSOR_DB_PATH)
            .orderBy(FULLNAME)
            .startAt(name.makeFirstCharCapital())
            .endAt(name.makeFirstCharCapital() + "\uf8ff")
            .get()
            .await()
            .toObjects(ProfessorDto::class.java)

        val professors = ProfessorsMapper.toDomainModel(result)
        emit(professors)
    }
}