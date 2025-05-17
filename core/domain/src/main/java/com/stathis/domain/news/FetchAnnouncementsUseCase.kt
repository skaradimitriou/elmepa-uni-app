package com.stathis.domain.news

import com.stathis.data.repository.NewsRepository
import javax.inject.Inject

class FetchAnnouncementsUseCase @Inject constructor(
    private val repo: NewsRepository
) {

    operator fun invoke() = repo.fetchAnnouncementFromRemote()
}
