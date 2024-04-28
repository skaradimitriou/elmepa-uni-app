package com.stathis.data.repository

import android.app.Application
import com.stathis.data.util.readLocalJsonList
import com.stathis.domain.repository.ResearchRepository
import com.stathis.model.research.ResearchResponse
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ResearchRepositoryImpl @Inject constructor(
    private val app: Application
) : ResearchRepository {

    override suspend fun fetchResearchDetails() = flow {
        app.readLocalJsonList<ResearchResponse>("research.json", data = {
            emit(it)
        })
    }
}