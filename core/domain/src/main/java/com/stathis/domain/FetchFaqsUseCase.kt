package com.stathis.domain

import com.stathis.common.base.BaseUseCase
import com.stathis.data.repository.FaqRepository
import com.stathis.model.UiModel
import com.stathis.model.network.NetworkResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchFaqsUseCase @Inject constructor(
    private val repo: FaqRepository
) : BaseUseCase<Flow<NetworkResult<List<UiModel>>>> {

    override suspend fun invoke(vararg args: Any?) = repo.fetchFaqs()
}