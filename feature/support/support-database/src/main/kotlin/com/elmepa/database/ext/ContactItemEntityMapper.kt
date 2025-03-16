package com.elmepa.database.ext

import com.elmepa.database.model.ContactItemEntity
import com.elmepa.support.model.ContactItem

fun ContactItem.toEntity() = ContactItemEntity(
    title = title,
    email = email,
    telephone = telephone,
    descriptionLine1 = descriptionLine1,
    descriptionLine2 = descriptionLine2,
    descriptionLine3 = descriptionLine3,
    contactType = contactType
)

fun ContactItemEntity.toContactItem() = ContactItem(
    title = title,
    email = email,
    telephone = telephone,
    descriptionLine1 = descriptionLine1,
    descriptionLine2 = descriptionLine2,
    descriptionLine3 = descriptionLine3,
    contactType = contactType
)
