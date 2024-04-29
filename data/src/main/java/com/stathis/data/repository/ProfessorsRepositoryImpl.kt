package com.stathis.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.stathis.core.util.firstCharCapital
import com.stathis.data.datasource.remote.mapper.ProfessorsMapper
import com.stathis.data.datasource.remote.model.ProfessorDto
import com.stathis.data.util.FULLNAME
import com.stathis.data.util.PROFESSOR_DB_PATH
import com.stathis.domain.repository.ProfessorsRepository
import com.stathis.model.professors.Professor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ProfessorsRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) : ProfessorsRepository {

    override suspend fun fetchAllProfessors(): Flow<List<Professor>> = flow {
        val result = firestore.collection(PROFESSOR_DB_PATH)
            .orderBy(FULLNAME)
            .get()
            .await()
            .toObjects(ProfessorDto::class.java)

        val professors = ProfessorsMapper.toDomainModel(result)
        emit(professors)
    }

    override suspend fun searchForProfessor(name: String): Flow<List<Professor>> = flow {
        val result = firestore.collection(PROFESSOR_DB_PATH)
            .orderBy(FULLNAME)
            .startAt(name.firstCharCapital())
            .endAt(name.firstCharCapital() + "\uf8ff")
            .get()
            .await()
            .toObjects(ProfessorDto::class.java)

        val professors = ProfessorsMapper.toDomainModel(result)
        emit(professors)
    }
}