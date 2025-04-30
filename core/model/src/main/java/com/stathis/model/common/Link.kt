package com.stathis.model.common

import android.os.Parcelable
import com.stathis.model.UiModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class Link(
    val title: String = "",
    val openUrl: String = "",
    val type: LinkType = LinkType.UNKNOWN
) : UiModel, Parcelable {

    override fun equalsContent(obj: UiModel) = false
}

enum class LinkType {
    UNKNOWN,
    CV,
    MAIL,
    RESEARCH_GATE,
    LINKEDIN,
    GOOGLE_SCHOLAR
}
