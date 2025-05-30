package com.elmepa.news.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

private const val ANNOUNCEMENTS_TABLE_NAME = "announcements"

@Entity(tableName = ANNOUNCEMENTS_TABLE_NAME)
data class AnnouncementEntity(

    @PrimaryKey(autoGenerate = false)
    val id: UUID = UUID.randomUUID(),

    val title: String,
    val description: String,
    val url: String,
    val pubDate: String,
    val image: String
)
