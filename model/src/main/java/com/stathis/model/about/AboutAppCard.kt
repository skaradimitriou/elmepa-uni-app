package com.stathis.model.about

import com.stathis.model.UiModel

data class AboutAppCard(
    val date: String,
    val description: String,
    val btnTxt: String
) : UiModel {
    override fun equalsContent(obj: UiModel) = when (obj) {
        is AboutAppCard -> date == obj.date && description == obj.description
        else -> false
    }
}
