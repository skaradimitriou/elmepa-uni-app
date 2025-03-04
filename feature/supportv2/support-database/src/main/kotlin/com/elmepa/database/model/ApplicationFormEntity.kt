package com.elmepa.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

const val APPLICATION_FORMS_TABLE_NAME = "application_forms"

@Entity(tableName = APPLICATION_FORMS_TABLE_NAME)
data class ApplicationFormEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val openUrl: String
)
