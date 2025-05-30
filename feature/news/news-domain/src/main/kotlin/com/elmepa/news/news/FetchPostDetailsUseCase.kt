package com.elmepa.news.news

import com.elmepa.news.repository.NewsRepository
import com.stathis.model.network.NetworkResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchPostDetailsUseCase @Inject constructor(
    private val repo: NewsRepository
) {

    operator fun invoke(urlToScrape: String): Flow<NetworkResult<String>> {
        require(urlToScrape.isNotEmpty()) {
            error("Post details scrape url can't be empty.")
        }

        return repo.fetchPostDetails(urlToScrape)
    }
}
