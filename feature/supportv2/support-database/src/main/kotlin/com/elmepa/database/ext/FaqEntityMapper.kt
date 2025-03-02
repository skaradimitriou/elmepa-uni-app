package com.elmepa.database.ext

import com.elmepa.database.model.FaqEntity
import com.stathis.model.support.Faq

fun Faq.toEntity() = FaqEntity(
    seq = seq,
    question = question,
    answer = answer
)

fun FaqEntity.toFaq() = Faq(
    seq = seq,
    question = question,
    answer = answer
)
