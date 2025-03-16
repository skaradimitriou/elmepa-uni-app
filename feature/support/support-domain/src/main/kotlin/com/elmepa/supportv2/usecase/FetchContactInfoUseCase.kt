package com.elmepa.support.usecase

import com.elmepa.support.repository.SupportRepository
import javax.inject.Inject

class FetchContactInfoUseCase @Inject constructor(
    private val repository: SupportRepository
) {

    operator fun invoke() = repository.fetchContactInfo()
}
