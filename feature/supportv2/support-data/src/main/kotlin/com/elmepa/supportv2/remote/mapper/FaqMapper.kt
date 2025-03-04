package com.elmepa.supportv2.remote.mapper

import com.stathis.common.util.toListOf
import com.stathis.common.util.toNotNull
import com.stathis.data.remote.mapper.BaseMapper
import com.stathis.data.remote.model.support.FaqDto
import com.stathis.model.support.Faq

internal object FaqMapper : BaseMapper<List<FaqDto>?, List<Faq>> {

    override fun toDomainModel(dtoModel: List<FaqDto>?) = dtoModel.toListOf {
        Faq(
            question = it.question.toNotNull(),
            answer = it.answer.toNotNull(),
            seq = it.seq.toNotNull()
        )
    }
}
