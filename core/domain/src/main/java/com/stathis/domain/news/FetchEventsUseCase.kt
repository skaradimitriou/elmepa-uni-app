package com.stathis.domain.news

import com.stathis.data.repository.NewsRepository
import javax.inject.Inject

class FetchEventsUseCase @Inject constructor(
    private val repo: NewsRepository
) {

    fun invoke() = repo.fetchEventsFromRemote()
}