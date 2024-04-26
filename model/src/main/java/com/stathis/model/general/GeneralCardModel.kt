package com.stathis.model.general

import com.stathis.core.base.UiModel

data class GeneralCardModel(
    val image: Int,
    val title: String,
    val description: String
) : UiModel {
    override fun equalsContent(obj: UiModel) = when (obj) {
        is GeneralCardModel -> title == obj.title
        else -> false
    }
}
