package com.stathis.common.bottomsheet

import com.stathis.model.UiModel
import java.io.Serializable

data class BottomSheetOption(
    val title: String,
    val showSeparator: Boolean = false,
    val type: OptionAction
) : UiModel, Serializable {
    override fun equalsContent(obj: UiModel) = when (obj) {
        is BottomSheetOption -> title == obj.title && showSeparator == obj.showSeparator && type == obj.type
        else -> false
    }
}

enum class OptionAction : Serializable {
    SHARE,
    SEND_EMAIL
}