package com.elmepa.supportv2.usecase

import com.elmepa.supportv2.repository.SupportRepository
import javax.inject.Inject

class FetchFaqsUseCase @Inject constructor(
    private val repo: SupportRepository
) {

    operator fun invoke() = repo.fetchFaqs()
}
