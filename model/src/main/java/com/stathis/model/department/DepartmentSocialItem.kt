package com.stathis.model.department

import com.stathis.core.base.UiModel

data class DepartmentSocialItem(
    val socialItems: List<UiModel>
) : UiModel {
    override fun equalsContent(obj: UiModel) = when (obj) {
        is DepartmentSocialItem -> socialItems == obj.socialItems
        else -> false
    }
}

data class SocialItem(
    val title: String,
    val imageUrl: String,
    val openUrl: String
) : UiModel {
    override fun equalsContent(obj: UiModel) = when (obj) {
        is SocialItem -> title == obj.title
        else -> false
    }
}
