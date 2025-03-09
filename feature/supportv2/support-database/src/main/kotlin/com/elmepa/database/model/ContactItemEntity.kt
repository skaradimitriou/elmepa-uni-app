package com.elmepa.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.elmepa.supportv2.model.ContactType

const val CONTACT_ITEM_TABLE_NAME = "contact_items"

@Entity(tableName = CONTACT_ITEM_TABLE_NAME)
data class ContactItemEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val title: String,
    val email: String,
    val telephone: String,
    val descriptionLine1: String,
    val descriptionLine2: String,
    val descriptionLine3: String,
    val contactType: ContactType
)
