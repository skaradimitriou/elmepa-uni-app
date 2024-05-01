package com.stathis.model.contact

import com.stathis.core.base.UiModel


data class ContactItem(
    val title: String,
    val email: String,
    val telephone: String,
    val descriptionLine1: String,
    val descriptionLine2: String,
    val descriptionLine3: String,
    val contactType: ContactType
) : UiModel {
    override fun equalsContent(obj: UiModel): Boolean = when (obj) {
        is ContactItem -> title == obj.title && contactType == obj.contactType
        else -> false
    }
}
