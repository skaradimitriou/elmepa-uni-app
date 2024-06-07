package com.stathis.domain.news

import com.stathis.common.base.BaseUseCase
import com.stathis.data.repository.NewsRepository
import com.stathis.model.announcements.details.PostDetailsRequest
import com.stathis.model.announcements.details.PostDetailsResponse
import com.stathis.model.network.NetworkResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchPostDetailsUseCase @Inject constructor(
    private val repo: NewsRepository
) : BaseUseCase<Flow<NetworkResult<PostDetailsResponse>>> {

    override suspend fun invoke(vararg args: Any?): Flow<NetworkResult<PostDetailsResponse>> {
        val request = args.getOrNull(0) as PostDetailsRequest
        return repo.fetchPostDetails(request)
    }
}