package com.stathis.data.remote.mapper

import com.stathis.common.util.toNotNull
import com.stathis.data.remote.model.DepartmentResponseDto
import com.stathis.data.remote.model.ProgrammeItemDto
import com.stathis.data.remote.model.SocialItemDto
import com.stathis.data.remote.model.SyllabusItemDto
import com.stathis.model.UiModel
import com.stathis.model.department.DepartmentProgrammeItem
import com.stathis.model.department.DepartmentSocialItem
import com.stathis.model.department.FieldOfStudy
import com.stathis.model.department.FieldOfStudyParent
import com.stathis.model.department.ProgrammeItem
import com.stathis.model.department.SocialItem

object DepartmentResponseMapper : BaseMapper<DepartmentResponseDto?, List<UiModel>> {

    override fun toDomainModel(dtoModel: DepartmentResponseDto?): List<UiModel> = listOf(
        CarouselMapper.toDomainModel(dtoModel?.carouselItems?.sortedBy { it.position }),
        FieldOfStudyParent(
            syllabusItems = dtoModel?.syllabusItems.toNotNull()
        ),
        DepartmentProgrammeItem(
            programmes = dtoModel?.programmes.toNotNull()
        ),
        DepartmentSocialItem(
            socialItems = dtoModel?.social.toNotNull()
        )
    )

    @JvmName("toNotNullSyllabusItems")
    private fun List<SyllabusItemDto>?.toNotNull() = this?.map { item ->
        FieldOfStudy(
            title = item.title.toNotNull(),
            imageUrl = item.imageUrl.toNotNull()
        )
    }.toNotNull()

    @JvmName("toNotNullProgrammes")
    private fun List<ProgrammeItemDto>?.toNotNull() = this?.map { item ->
        ProgrammeItem(
            title = item.title.toNotNull(),
            description = item.description.toNotNull(),
            imageUrl = item.imageUrl.toNotNull(),
            openUrl = item.openUrl.toNotNull(),
            openInBrowser = item.openInBrowser.toNotNull()
        )
    }.toNotNull()

    @JvmName("toNotNullSocialItems")
    private fun List<SocialItemDto>?.toNotNull() = this?.map { item ->
        SocialItem(
            title = item.title.toNotNull(),
            imageUrl = item.imageUrl.toNotNull(),
            openUrl = item.openUrl.toNotNull(),
            openInBrowser = item.openInBrowser.toNotNull()
        )
    }.toNotNull()
}