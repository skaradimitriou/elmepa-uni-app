package com.stathis.data.remote.mapper.support

import com.stathis.common.util.toNotNull
import com.stathis.data.remote.mapper.BaseMapper
import com.stathis.data.remote.model.support.FaqDto
import com.stathis.model.support.Faq

object FaqMapper : BaseMapper<List<FaqDto>?, List<Faq>> {

    override fun toDomainModel(dtoModel: List<FaqDto>?) = dtoModel?.map {
        Faq(
            question = it.question.toNotNull(),
            answer = it.answer.toNotNull(),
            seq = it.seq.toNotNull()
        )
    }.toNotNull()
}