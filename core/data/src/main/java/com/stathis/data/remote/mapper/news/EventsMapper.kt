package com.stathis.data.remote.mapper.news

import com.stathis.common.util.toListOf
import com.stathis.common.util.toNotNull
import com.stathis.data.remote.mapper.BaseMapper
import com.stathis.data.remote.model.announcements.EventDto
import com.stathis.model.announcements.Event

object EventsMapper : BaseMapper<List<EventDto>?, List<Event>> {

    override fun toDomainModel(dtoModel: List<EventDto>?) = dtoModel.toListOf { model ->
        Event(
            name = model.title.toNotNull(),
            description = model.description.toNotNull(),
            imageResource = model.imageUrl.toNotNull(),
            pubDate = model.pubDate.toNotNull().substringBefore('|'),
            url = model.openUrl.toNotNull()
        )
    }
}