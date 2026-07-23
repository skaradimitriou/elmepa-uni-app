package com.students.data.remote.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.stathis.domain.model.DomainResult
import com.students.data.remote.dto.StudentsScreenResponseDto
import com.students.data.remote.mapper.toDomain
import com.students.domain.model.StudentSection
import com.students.domain.repository.StudentsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

private const val STUDENTS_BFF_COLLECTION = "bff"
private const val STUDENTS_BFF_DOCUMENT = "students"

internal class StudentsRepositoryImpl @Inject constructor(
    private val fireStore: FirebaseFirestore,
) : StudentsRepository {

    override suspend fun getStudentScreenInfo(): Flow<DomainResult<List<StudentSection>>> = flow {
        emit(DomainResult.Loading())

        val response = fireStore.collection(STUDENTS_BFF_COLLECTION)
            .document(STUDENTS_BFF_DOCUMENT)
            .get()
            .await()
            .toObject(StudentsScreenResponseDto::class.java)

        val result = response?.let { dto ->
            DomainResult.Success(dto.toDomain())
        } ?: DomainResult.Error(Throwable())

        emit(result)
    }
}
