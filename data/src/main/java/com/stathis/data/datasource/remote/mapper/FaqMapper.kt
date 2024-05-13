package com.stathis.data.datasource.remote.mapper

import com.stathis.core.util.toNotNull
import com.stathis.data.datasource.remote.model.FaqDto
import com.stathis.model.faq.Faq

object FaqMapper : BaseMapper<List<FaqDto>?, List<Faq>> {

    override fun toDomainModel(dtoModel: List<FaqDto>?) = dtoModel?.map {
        Faq(
            question = it.question.toNotNull(),
            answer = it.answer.toNotNull(),
            seq = it.seq.toNotNull()
        )
    }.toNotNull()
}