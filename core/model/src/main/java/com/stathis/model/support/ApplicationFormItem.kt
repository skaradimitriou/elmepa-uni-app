package com.stathis.model.support

import com.stathis.model.UiModel

data class ApplicationFormItem(
    val title: String,
    val openUrl: String
) : UiModel {
    override fun equalsContent(obj: UiModel) = when (obj) {
        is ApplicationFormItem -> title == obj.title && openUrl == obj.openUrl
        else -> false
    }
}