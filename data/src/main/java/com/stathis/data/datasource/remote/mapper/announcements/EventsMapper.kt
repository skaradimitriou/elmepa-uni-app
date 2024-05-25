package com.stathis.data.datasource.remote.mapper.announcements

import com.stathis.core.util.toListOf
import com.stathis.core.util.toNotNull
import com.stathis.data.datasource.remote.mapper.BaseMapper
import com.stathis.data.datasource.remote.model.announcements.EventDto
import com.stathis.model.announcements.Event

object EventsMapper : BaseMapper<List<EventDto>?, List<Event>> {

    override fun toDomainModel(dtoModel: List<EventDto>?) = dtoModel.toListOf { model ->
        Event(
            name = model.title.toNotNull(),
            imageResource = model.imageUrl.toNotNull(),
            pubDate = model.pubDate.toNotNull(),
            url = model.openUrl.toNotNull()
        )
    }
}