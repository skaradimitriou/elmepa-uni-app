package com.stathis.model.common

import com.stathis.model.UiModel

data class Header(
    val title: String
) : UiModel {
    override fun equalsContent(obj: UiModel) = false
}