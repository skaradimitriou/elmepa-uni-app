package com.elmepa.personnel.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

private const val PEOPLE = "people"

@Entity(tableName = PEOPLE)
data class PersonEntity(

    @PrimaryKey(autoGenerate = false)
    val id: UUID = UUID.randomUUID(),

    val fullName: String,
    val description: String,
    val image: String,
    val email: String,
    val gender: String,
    val vocative: String
)
