package com.elmepa.personnel.model

import com.stathis.model.UiModel
import java.util.UUID

class Person(
    val id: UUID = UUID.randomUUID(),
    val fullName: String,
    val description: String,
    val image: String,
    val email: String,
    val gender: Gender,
    val vocative: String
) : UiModel {

    override fun equalsContent(obj: UiModel): Boolean = false
}
