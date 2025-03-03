package com.stathis.data.repository

import com.stathis.data.remote.mapper.support.ApplicationFormMapper
import com.stathis.data.remote.model.support.ApplicationFormItemDto
import com.stathis.data.util.APPLICATION_FORMS_URL
import com.stathis.data.util.DIV_CONTENT
import com.stathis.data.util.LI
import com.stathis.data.util.UL
import com.stathis.data.util.getUrlText
import com.stathis.model.UiModel
import com.stathis.model.network.NetworkResult
import com.stathis.model.util.ShimmerGenerator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.jsoup.Jsoup

class SupportRepositoryImpl() : SupportRepository {

    override suspend fun fetchApplicationForms(): Flow<NetworkResult<List<UiModel>>> = flow {
        try {
            emit(NetworkResult.Loading(ShimmerGenerator.list))
            val dtoModels = Jsoup.connect(APPLICATION_FORMS_URL).get()
                .select(DIV_CONTENT).select(UL).map { html ->
                    html.select(LI).select(UL).select(LI).map { listItem ->
                        val title = listItem.text()
                        val openUrl = listItem.getUrlText()
                        ApplicationFormItemDto(title, openUrl)
                    }
                }

            val domainModels = ApplicationFormMapper.toDomainModel(dtoModels.flatten())
            emit(NetworkResult.Success(domainModels))
        } catch (e: Exception) {
            emit(NetworkResult.Failure(e.localizedMessage))
        }
    }
}
