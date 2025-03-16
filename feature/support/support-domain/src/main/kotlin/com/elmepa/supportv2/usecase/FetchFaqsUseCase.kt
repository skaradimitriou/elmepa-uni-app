package com.elmepa.support.usecase

import com.elmepa.support.repository.SupportRepository
import javax.inject.Inject

class FetchFaqsUseCase @Inject constructor(
    private val repo: SupportRepository
) {

    operator fun invoke() = repo.fetchFaqs()
}
