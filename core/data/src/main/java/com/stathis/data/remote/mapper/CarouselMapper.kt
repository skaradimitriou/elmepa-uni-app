package com.stathis.data.remote.mapper

import com.stathis.common.util.toNotNull
import com.stathis.data.remote.model.CarouselItemDto
import com.stathis.model.general.carousel.CarouselItem
import com.stathis.model.general.carousel.CarouselParent

object CarouselMapper : BaseMapper<List<CarouselItemDto>?, CarouselParent> {

    override fun toDomainModel(dtoModel: List<CarouselItemDto>?): CarouselParent {
        val data = dtoModel?.map { it.toDomainModel() }.toNotNull()
        return CarouselParent(carouselItems = data)
    }

    private fun CarouselItemDto?.toDomainModel() = CarouselItem(
        title = this?.title.toNotNull(),
        webTitle = this?.webTitle.toNotNull(),
        imageUrl = this?.imageUrl.toNotNull(),
        openUrl = this?.openUrl.toNotNull(),
        position = this?.position.toNotNull(),
        openInBrowser = this?.openInBrowser.toNotNull()
    )
}