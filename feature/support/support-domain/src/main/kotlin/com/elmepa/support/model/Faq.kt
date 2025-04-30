package com.elmepa.support.model

import com.stathis.model.UiModel

data class Faq(
    val question: String,
    val answer: String,
    val seq: Int,
    var isExpanded: Boolean = false
) : UiModel {

    override fun equalsContent(obj: UiModel) = when (obj) {
        is Faq -> question == obj.question && answer == obj.answer
        else -> false
    }
}
