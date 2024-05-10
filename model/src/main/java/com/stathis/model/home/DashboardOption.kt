package com.stathis.model.home

import com.stathis.core.base.UiModel

data class DashboardOption(
    val title: String,
    val drawable: Int,
    val type: OptionType
) : UiModel {
    override fun equalsContent(obj: UiModel) = when (obj) {
        is DashboardOption -> title == obj.title && drawable == obj.drawable
        else -> false
    }
}

enum class OptionType {
    ANNOUNCEMENTS,
    STUDENTS,
    DEPARTMENT,
    RESEARCH,
    FAQ,
    CONTACT,
    ABOUT
}