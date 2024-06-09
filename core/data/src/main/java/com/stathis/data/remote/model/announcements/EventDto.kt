package com.stathis.data.remote.model.announcements

data class EventDto(
    val title: String? = null,
    val description: String? = null,
    val imageUrl: String? = null,
    val openUrl: String? = null,
    val pubDate: String? = null
)