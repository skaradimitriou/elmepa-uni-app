package com.stathis.data.datasource.remote.mapper

import com.stathis.core.base.UiModel
import com.stathis.core.util.toNotNull
import com.stathis.data.datasource.remote.model.CarouselItemDto
import com.stathis.data.datasource.remote.model.LinkDto
import com.stathis.data.datasource.remote.model.StudentsResponseDto
import com.stathis.model.general.carousel.CarouselItem
import com.stathis.model.general.carousel.CarouselParent
import com.stathis.model.students.Link
import com.stathis.model.students.LinkParent

object StudentsMapper : BaseMapper<StudentsResponseDto?, List<UiModel>> {

    override fun toDomainModel(dtoModel: StudentsResponseDto?) = listOf(
        dtoModel?.carouselItems.toDomainModel(),
        dtoModel?.links.toDomainModel()
    )

    private fun List<CarouselItemDto>?.toDomainModel(): CarouselParent {
        val data = this?.map { it.toDomainModel() }.toNotNull()
        return CarouselParent(carouselItems = data)
    }

    private fun List<LinkDto>?.toDomainModel(): LinkParent {
        val data = this?.map { it.toDomainModel() }.toNotNull()
        return LinkParent(links = data)
    }

    private fun CarouselItemDto?.toDomainModel() = CarouselItem(
        title = this?.title.toNotNull(),
        webTitle = this?.webTitle.toNotNull(),
        imageUrl = this?.imageUrl.toNotNull(),
        openUrl = this?.openUrl.toNotNull(),
        position = this?.position.toNotNull(),
    )

    private fun LinkDto?.toDomainModel() = Link(
        title = this?.title.toNotNull(),
        imageUrl = this?.imageUrl.toNotNull(),
        openUrl = this?.openUrl.toNotNull()
    )
}