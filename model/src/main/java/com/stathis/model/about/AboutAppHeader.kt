package com.stathis.model.about

import com.stathis.model.UiModel

data class AboutAppHeader(
    val header: String,
    val title: String,
    val description: String
) : UiModel {
    override fun equalsContent(obj: UiModel) = when (obj) {
        is AboutAppHeader -> description == obj.description
        else -> false
    }
}