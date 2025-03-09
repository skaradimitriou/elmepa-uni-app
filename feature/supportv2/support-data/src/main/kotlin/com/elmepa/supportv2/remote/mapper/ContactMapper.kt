package com.elmepa.supportv2.remote.mapper

import com.elmepa.supportv2.model.ContactItem
import com.elmepa.supportv2.model.ContactType
import com.stathis.common.util.toNotNull
import com.stathis.data.remote.mapper.BaseMapper
import com.stathis.data.remote.model.ContactItemDto

object ContactMapper : BaseMapper<List<ContactItemDto>?, List<ContactItem>> {

    override fun toDomainModel(dtoModel: List<ContactItemDto>?) = dtoModel?.map {
        it.toDomainModel()
    }.toNotNull()

    private fun ContactItemDto?.toDomainModel() = ContactItem(
        title = this?.title.toNotNull(),
        email = this?.email.toNotNull(),
        telephone = this?.telephone.toNotNull(),
        descriptionLine1 = this?.descriptionLine1.toNotNull(),
        descriptionLine2 = this?.descriptionLine2.toNotNull(),
        descriptionLine3 = this?.descriptionLine3.toNotNull(),
        contactType = ContactType.valueOf(this?.contactType ?: "UNDEFINED")
    )
}
