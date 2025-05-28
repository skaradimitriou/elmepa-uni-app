package com.elmepa.news.news

import com.elmepa.news.repository.NewsRepository
import javax.inject.Inject

class FetchAnnouncementsUseCase @Inject constructor(
    private val repo: NewsRepository
) {

    operator fun invoke() = repo.fetchAnnouncementFromRemote()
}
