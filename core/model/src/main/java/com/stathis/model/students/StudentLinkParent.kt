package com.stathis.model.students

import com.stathis.model.UiModel

data class StudentLinkParent(
    val links: List<UiModel>
) : UiModel {
    override fun equalsContent(obj: UiModel) = when (obj) {
        is StudentLinkParent -> links == obj.links
        else -> false
    }
}