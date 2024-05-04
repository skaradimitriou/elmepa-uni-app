package com.stathis.model.students

import com.stathis.core.base.UiModel

data class LinkParent(
    val links: List<Link>
) : UiModel {
    override fun equalsContent(obj: UiModel) = when (obj) {
        is LinkParent -> links == obj.links
        else -> false
    }
}