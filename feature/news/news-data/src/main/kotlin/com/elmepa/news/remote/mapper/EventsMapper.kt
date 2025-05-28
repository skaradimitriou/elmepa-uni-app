package com.elmepa.news.remote.mapper

import com.elmepa.news.model.Event
import com.elmepa.news.remote.model.EventDto
import com.stathis.common.util.toListOf
import com.stathis.data.remote.mapper.BaseMapper

internal object EventsMapper : BaseMapper<List<EventDto>?, List<Event>> {

    override fun toDomainModel(dtoModel: List<EventDto>?) = dtoModel.toListOf { model ->
        Event(
            title = model.title.orEmpty(),
            description = model.description.orEmpty(),
            image = model.imageUrl.orEmpty(),
            pubDate = model.pubDate.orEmpty().substringBefore('|'),
            url = model.openUrl.orEmpty()
        )
    }
}
