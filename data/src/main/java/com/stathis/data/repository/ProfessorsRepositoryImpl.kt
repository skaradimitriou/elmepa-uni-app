package com.stathis.data.repository

import android.app.Application
import com.stathis.data.util.equalsName
import com.stathis.data.util.readLocalJsonList
import com.stathis.data.util.sortedAlphabetically
import com.stathis.domain.repository.ProfessorsRepository
import com.stathis.model.professors.Professor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProfessorsRepositoryImpl @Inject constructor(
    private val app: Application
) : ProfessorsRepository {

    //FIXME: Replace local .json file with Firebase Firestore later on

    override suspend fun fetchAllProfessors(): Flow<List<Professor>> = flow {
        app.readLocalJsonList<Professor>("professors.json", data = { list ->
            val sortedList = list.sortedAlphabetically()
            emit(sortedList)
        })
    }

    override suspend fun searchForProfessor(name: String): Flow<List<Professor>> = flow {
        app.readLocalJsonList<Professor>("professors.json", data = { list ->
            val filteredList = list.filter { it.equalsName(name) }.sortedAlphabetically()
            emit(filteredList)
        })
    }
}