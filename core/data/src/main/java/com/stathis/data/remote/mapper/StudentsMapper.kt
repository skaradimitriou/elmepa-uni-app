package com.stathis.data.remote.mapper

import com.stathis.common.util.toNotNull
import com.stathis.data.remote.model.LinkDto
import com.stathis.data.remote.model.StudentsResponseDto
import com.stathis.model.UiModel
import com.stathis.model.students.StudentLink
import com.stathis.model.students.StudentLinkParent

object StudentsMapper : BaseMapper<StudentsResponseDto?, List<UiModel>> {

    override fun toDomainModel(dtoModel: StudentsResponseDto?) = listOf(
        CarouselMapper.toDomainModel(dtoModel?.carouselItems),
        dtoModel?.links.toDomainModel()
    )

    private fun List<LinkDto>?.toDomainModel(): StudentLinkParent {
        val data = this?.map { it.toDomainModel() }.toNotNull()
        return StudentLinkParent(links = data)
    }

    private fun LinkDto?.toDomainModel() = StudentLink(
        title = this?.title.toNotNull(),
        imageUrl = this?.imageUrl.toNotNull(),
        openUrl = this?.openUrl.toNotNull(),
        openInBrowser = this?.openInBrowser.toNotNull()
    )
}