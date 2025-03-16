package com.elmepa.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

const val FAQ_TABLE_NAME = "Faqs"

@Entity(tableName = FAQ_TABLE_NAME)
data class FaqEntity(

    @PrimaryKey(autoGenerate = false)
    val seq: Int,
    val question: String,
    val answer: String,
)
