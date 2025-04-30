package com.elmepa.support.remote.mapper

import com.elmepa.support.model.Faq
import com.elmepa.support.remote.model.FaqDto
import com.stathis.common.util.toListOf
import com.stathis.common.util.toNotNull
import com.stathis.data.remote.mapper.BaseMapper

internal object FaqMapper : BaseMapper<List<FaqDto>?, List<Faq>> {

    override fun toDomainModel(dtoModel: List<FaqDto>?) = dtoModel.toListOf {
        Faq(
            question = it.question.toNotNull(),
            answer = it.answer.toNotNull(),
            seq = it.seq.toNotNull()
        )
    }
}
