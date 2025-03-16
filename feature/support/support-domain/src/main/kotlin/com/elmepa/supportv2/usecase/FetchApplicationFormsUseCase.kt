package com.elmepa.support.usecase

import com.elmepa.support.repository.SupportRepository
import javax.inject.Inject

class FetchApplicationFormsUseCase @Inject constructor(
    private val repository: SupportRepository
) {

    operator fun invoke() = repository.fetchApplicationForms()
}
