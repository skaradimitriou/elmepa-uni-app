package com.elmepa.supportv2.repository

import com.stathis.domain.model.DomainResult
import com.stathis.model.support.Faq
import kotlinx.coroutines.flow.Flow

interface SupportRepository {

    fun fetchFaqs(): Flow<DomainResult<List<Faq>>>
}
