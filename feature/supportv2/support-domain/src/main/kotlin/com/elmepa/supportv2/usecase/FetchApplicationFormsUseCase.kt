package com.elmepa.supportv2.usecase

import com.elmepa.supportv2.repository.SupportRepository
import javax.inject.Inject

class FetchApplicationFormsUseCase @Inject constructor(
    private val repository: SupportRepository
) {

    operator fun invoke() = repository.fetchApplicationForms()
}
