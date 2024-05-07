package com.stathis.data.datasource.remote.mapper

import com.stathis.core.base.UiModel
import com.stathis.core.util.toNotNull
import com.stathis.data.datasource.remote.model.LinkDto
import com.stathis.data.datasource.remote.model.StudentsResponseDto
import com.stathis.model.students.Link
import com.stathis.model.students.LinkParent

object StudentsMapper : BaseMapper<StudentsResponseDto?, List<UiModel>> {

    override fun toDomainModel(dtoModel: StudentsResponseDto?) = listOf(
        CarouselMapper.toDomainModel(dtoModel?.carouselItems),
        dtoModel?.links.toDomainModel()
    )

    private fun List<LinkDto>?.toDomainModel(): LinkParent {
        val data = this?.map { it.toDomainModel() }.toNotNull()
        return LinkParent(links = data)
    }

    private fun LinkDto?.toDomainModel() = Link(
        title = this?.title.toNotNull(),
        imageUrl = this?.imageUrl.toNotNull(),
        openUrl = this?.openUrl.toNotNull()
    )
}