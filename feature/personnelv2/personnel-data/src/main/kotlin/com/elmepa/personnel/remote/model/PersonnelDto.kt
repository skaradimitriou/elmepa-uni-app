package com.elmepa.personnel.remote.model

internal data class PersonnelDto(
    val fullName: String? = null,
    val description: String? = null,
    val image: String? = null,
    val email: String? = null,
    val gender: String? = null,
    val vocative: String? = null
)
