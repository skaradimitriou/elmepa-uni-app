package com.stathis.domain.announcements

import com.stathis.common.base.BaseUseCase
import com.stathis.data.repository.AnnouncementRepository
import com.stathis.model.UiModel
import com.stathis.model.network.NetworkResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchEventsUseCase @Inject constructor(
    private val repo: AnnouncementRepository
) : BaseUseCase<Flow<NetworkResult<List<UiModel>>>> {

    override suspend fun invoke(vararg args: Any?): Flow<NetworkResult<List<UiModel>>> {
        val forceUpdate = args.getOrNull(0) as? Boolean? ?: false
        return repo.fetchDepartmentEvents(forceUpdate)
    }
}