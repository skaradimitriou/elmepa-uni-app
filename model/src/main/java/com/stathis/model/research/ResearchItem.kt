package com.stathis.model.research

import com.stathis.core.base.UiModel

data class ResearchItem(
    val name: String,
    val url: String,
    val imageResource: String
) : UiModel {
    override fun equalsContent(obj: UiModel): Boolean = when (obj) {
        is ResearchItem -> name == obj.name && url == obj.url && imageResource == obj.imageResource
        else -> false
    }
}
