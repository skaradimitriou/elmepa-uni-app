package com.elmepa.support.model

data class ContactItem(
    val title: String,
    val email: String,
    val telephone: String,
    val descriptionLine1: String,
    val descriptionLine2: String,
    val descriptionLine3: String,
    val contactType: ContactType
) {

    val description: String
        get() = listOf(descriptionLine1, descriptionLine2, descriptionLine3)
            .filter { it.isNotEmpty() }
            .joinToString("\n")
}
