package com.elmepa.support.repository

import com.elmepa.support.model.ApplicationForm
import com.elmepa.support.model.ContactItem
import com.stathis.domain.model.DomainResult
import com.stathis.model.support.Faq
import kotlinx.coroutines.flow.Flow

interface SupportRepository {

    fun fetchFaqs(): Flow<DomainResult<List<Faq>>>

    fun fetchApplicationForms(): Flow<DomainResult<List<ApplicationForm>>>

    fun fetchContactInfo(): Flow<DomainResult<List<ContactItem>>>
}
