package com.elmepa.personnel.model

import java.util.UUID

class Person(
    val id: UUID = UUID.randomUUID(),
    val fullName: String,
    val description: String,
    val image: String,
    val email: String,
    val gender: Gender,
    val vocative: String
) {

    /**
     * Helper property that holds the person details to be shared.
     */
    val details = "$fullName, \n$email"
}
