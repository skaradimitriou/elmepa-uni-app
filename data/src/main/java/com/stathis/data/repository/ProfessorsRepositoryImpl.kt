package com.stathis.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.stathis.core.base.UiModel
import com.stathis.data.datasource.local.professors.ProfessorsDatabase
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
    private val fireStore: FirebaseFirestore,
    localDataSource: ProfessorsDatabase
) : ProfessorsRepository {

    private val professorsDao = localDataSource.professorsDao()

    override suspend fun fetchAllProfessors(): Flow<List<UiModel>> = flow {
        emit(ShimmerGenerator.list)

        val result = fireStore.collection(PROFESSOR_DB_PATH)
            .orderBy(FULLNAME)
            .get()
            .await()
            .toObjects(ProfessorDto::class.java)

        val professors = ProfessorsMapper.toDomainModel(result)

        professorsDao.deleteAll()
        professorsDao.insertAll(professors)

        professorsDao.getAllProfessors().collect {
            emit(professors)
        }
    }

    override suspend fun searchForProfessor(name: String): Flow<List<Professor>> = flow {
        professorsDao.queryProfessorsByFullname(name).collect { results ->
            emit(results)
        }
    }
}