package com.elmepa.news.mapper

import com.elmepa.news.db.entity.AnnouncementEntity
import com.elmepa.news.db.entity.EventEntity
import com.elmepa.news.model.Announcement
import com.elmepa.news.model.Event

fun Announcement.toEntity() = AnnouncementEntity(
    title = title,
    description = description,
    url = url,
    pubDate = pubDate,
    image = image
)

fun AnnouncementEntity.toModel() = Announcement(
    title = title,
    description = description,
    url = url,
    pubDate = pubDate,
    image = image
)

fun Event.toEntity() = EventEntity(
    title = title,
    description = description,
    url = url,
    pubDate = pubDate,
    image = image
)

fun EventEntity.toModel() = Event(
    title = title,
    description = description,
    url = url,
    pubDate = pubDate,
    image = image
)
