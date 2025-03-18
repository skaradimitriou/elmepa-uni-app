package com.elmepa.personnel.mapper

import com.elmepa.personnel.db.PersonEntity
import com.elmepa.personnel.model.Person

fun Person.toEntity() = PersonEntity(
    id = id,
    fullName = fullName,
    description = description,
    image = image,
    email = email,
    gender = gender,
    vocative = vocative
)

fun PersonEntity.toPerson() = Person(
    id = id,
    fullName = fullName,
    description = description,
    image = image,
    email = email,
    gender = gender,
    vocative = vocative
)
