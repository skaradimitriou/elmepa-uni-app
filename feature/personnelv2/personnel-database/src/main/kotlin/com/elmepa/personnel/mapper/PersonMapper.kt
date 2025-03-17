package com.elmepa.personnel.mapper

import com.elmepa.personnel.db.PersonEntity
import com.stathis.model.personnel.Person

fun Person.toEntity() = PersonEntity(
    fullName = fullName,
    description = description,
    image = image,
    email = email,
    gender = gender,
    vocative = vocative
)

fun PersonEntity.toPerson() = Person(
    fullName = fullName,
    description = description,
    image = image,
    email = email,
    gender = gender,
    vocative = vocative
)
