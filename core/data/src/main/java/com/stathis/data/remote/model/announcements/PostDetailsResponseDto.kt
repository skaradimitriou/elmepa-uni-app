package com.stathis.data.remote.model.announcements

data class PostDetailsResponseDto(
    val title: String? = null,
    val imageUrl: String? = null,
    val pubDate: String? = null,
    val htmlContent: String? = null
)