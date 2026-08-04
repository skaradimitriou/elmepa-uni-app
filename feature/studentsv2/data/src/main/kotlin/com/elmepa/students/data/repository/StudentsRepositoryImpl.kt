package com.elmepa.students.data.repository

import com.elmepa.students.data.remote.dto.StudentsScreenResponseDto
import com.elmepa.students.data.remote.mapper.toDomain
import com.google.firebase.firestore.FirebaseFirestore
import com.stathis.domain.model.DomainResult
import com.elmepa.students.domain.model.StudentSection
import com.elmepa.students.domain.repository.StudentsRepository
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
        } ?: DomainResult.Error(Throwable(message = "Failed to get data from remote for students"))

        emit(result)
    }
}
