package com.elmepa.news.remote.mapper

import com.elmepa.news.model.Announcement
import com.elmepa.news.remote.model.AnnouncementDto
import com.stathis.common.util.toListOf
import com.stathis.data.remote.mapper.BaseMapper

internal object AnnouncementsMapper : BaseMapper<List<AnnouncementDto>?, List<Announcement>> {

    override fun toDomainModel(dtoModel: List<AnnouncementDto>?) = dtoModel.toListOf { dto ->
        Announcement(
            title = dto.title.orEmpty(),
            description = dto.description.orEmpty(),
            url = dto.openUrl.orEmpty(),
            pubDate = dto.pubDate.orEmpty().substringBefore('|'),
            image = dto.imageUrl.orEmpty(),
        )
    }
}
