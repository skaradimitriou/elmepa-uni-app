package com.stathis.domain.usecase

import com.stathis.core.base.BaseUseCase
import com.stathis.core.base.UiModel
import com.stathis.domain.repository.AnnouncementRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchAnnouncementsUseCase @Inject constructor(
    private val repo: AnnouncementRepository
) : BaseUseCase<Flow<List<UiModel>>> {

    override suspend fun invoke(vararg args: Any?): Flow<List<UiModel>> {
        val forceUpdate = args.getOrNull(0) as? Boolean? ?: false
        return repo.fetchAnnouncements(forceUpdate)
    }
}