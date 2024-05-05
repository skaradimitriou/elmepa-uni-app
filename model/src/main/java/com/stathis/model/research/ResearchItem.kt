package com.stathis.model.research

import com.stathis.core.base.UiModel

data class ResearchItem(
    val name: String,
    val openUrl: String,
    val imageUrl: String
) : UiModel {
    override fun equalsContent(obj: UiModel): Boolean = when (obj) {
        is ResearchItem -> name == obj.name && openUrl == obj.openUrl && imageUrl == obj.imageUrl
        else -> false
    }
}
