package com.elmepa.personnel.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.elmepa.personnel.model.Gender
import java.util.UUID

private const val PERSONNEL = "personnel"

@Entity(tableName = PERSONNEL)
data class PersonEntity(

    @PrimaryKey(autoGenerate = false)
    val id: UUID = UUID.randomUUID(),

    val fullName: String,
    val description: String,
    val image: String,
    val email: String,
    val gender: Gender,
    val vocative: String
)
