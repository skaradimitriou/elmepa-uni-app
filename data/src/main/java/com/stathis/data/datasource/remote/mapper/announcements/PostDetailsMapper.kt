package com.stathis.data.datasource.remote.mapper.announcements

import com.stathis.core.util.toNotNull
import com.stathis.data.datasource.remote.mapper.BaseMapper
import com.stathis.data.datasource.remote.model.announcements.PostDetailsResponseDto
import com.stathis.model.announcements.details.PostDetailsResponse

object PostDetailsMapper : BaseMapper<PostDetailsResponseDto, PostDetailsResponse> {

    override fun toDomainModel(dtoModel: PostDetailsResponseDto?) = PostDetailsResponse(
        title = dtoModel?.title.toNotNull(),
        image = dtoModel?.imageUrl.toNotNull(),
        pubDate = dtoModel?.pubDate.toNotNull(),
        htmlContent = dtoModel?.htmlContent.toNotNull(),
    )
}