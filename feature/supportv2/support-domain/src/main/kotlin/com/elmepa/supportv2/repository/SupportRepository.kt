package com.elmepa.supportv2.repository

import com.elmepa.supportv2.model.ApplicationForm
import com.stathis.domain.model.DomainResult
import com.stathis.model.support.Faq
import kotlinx.coroutines.flow.Flow

interface SupportRepository {

    fun fetchFaqs(): Flow<DomainResult<List<Faq>>>

    fun fetchApplicationForms(): Flow<DomainResult<List<ApplicationForm>>>
}
