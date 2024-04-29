package com.stathis.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.stathis.core.util.toListOf
import com.stathis.data.datasource.remote.mapper.SemesterMapper
import com.stathis.data.datasource.remote.model.SemesterDto
import com.stathis.domain.repository.SyllabusRepository
import com.stathis.model.syllabus.Semester
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class SyllabusRepositoryImpl @Inject constructor(
    private val fireStore: FirebaseFirestore
) : SyllabusRepository {

    override suspend fun fetchSemesters(): Flow<List<Semester>> = flow {
        val result = fireStore.collection("semesters")
            .get()
            .await()
            .toListOf<SemesterDto>()

        val mappedResult = SemesterMapper.toDomainModel(result)
        emit(mappedResult)
    }
}