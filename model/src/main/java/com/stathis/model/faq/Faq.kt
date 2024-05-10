package com.stathis.model.faq

import com.stathis.core.base.UiModel

data class Faq(
    val question: String,
    val answer: String,
    var isExpanded: Boolean = false
) : UiModel {
    override fun equalsContent(obj: UiModel) = when (obj) {
        is Faq -> question == obj.question && answer == obj.answer
        else -> false
    }
}