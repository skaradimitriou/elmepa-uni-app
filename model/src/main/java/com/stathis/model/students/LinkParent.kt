package com.stathis.model.students

import com.stathis.model.UiModel

data class LinkParent(
    val links: List<UiModel>
) : UiModel {
    override fun equalsContent(obj: UiModel) = when (obj) {
        is LinkParent -> links == obj.links
        else -> false
    }
}