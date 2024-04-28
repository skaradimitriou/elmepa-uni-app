package com.stathis.model.research

import com.stathis.core.base.UiModel

data class ResearchResponse(
    val categoryName: String,
    val researchItems: List<ResearchItem>
) : UiModel {
    override fun equalsContent(obj: UiModel): Boolean = when (obj) {
        is ResearchResponse -> categoryName == obj.categoryName && researchItems == obj.researchItems
        else -> false
    }
}
