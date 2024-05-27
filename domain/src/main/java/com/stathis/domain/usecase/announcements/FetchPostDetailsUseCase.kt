package com.stathis.domain.usecase.announcements

import com.stathis.core.base.BaseUseCase
import com.stathis.domain.repository.AnnouncementRepository
import com.stathis.model.announcements.details.PostDetailsRequest
import com.stathis.model.announcements.details.PostDetailsResponse
import com.stathis.model.network.NetworkResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchPostDetailsUseCase @Inject constructor(
    private val repo: AnnouncementRepository
) : BaseUseCase<Flow<NetworkResult<PostDetailsResponse>>> {

    override suspend fun invoke(vararg args: Any?): Flow<NetworkResult<PostDetailsResponse>> {
        val request = args.getOrNull(0) as PostDetailsRequest
        return repo.fetchPostDetails(request)
    }
}