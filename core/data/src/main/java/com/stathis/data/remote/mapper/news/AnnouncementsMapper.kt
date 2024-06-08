package com.stathis.data.remote.mapper.news

import com.stathis.common.util.toListOf
import com.stathis.common.util.toNotNull
import com.stathis.data.remote.mapper.BaseMapper
import com.stathis.data.remote.model.announcements.AnnouncementDto
import com.stathis.model.announcements.Announcement

object AnnouncementsMapper : BaseMapper<List<AnnouncementDto>?, List<Announcement>> {

    override fun toDomainModel(dtoModel: List<AnnouncementDto>?) = dtoModel.toListOf { dto ->
        Announcement(
            name = dto.title.toNotNull(),
            url = dto.openUrl.toNotNull(),
            pubDate = dto.pubDate.toNotNull(),
            imageResource = dto.imageUrl.toNotNull(),
        )
    }
}