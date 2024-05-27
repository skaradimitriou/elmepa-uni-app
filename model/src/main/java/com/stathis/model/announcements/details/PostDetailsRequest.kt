package com.stathis.model.announcements.details

data class PostDetailsRequest(
    val title: String,
    val imageUrl: String,
    val pubDate: String,
    val scrapeUrl: String
)