package com.stathis.data.datasource.remote.mapper

import com.stathis.core.base.UiModel
import com.stathis.core.util.toNotNull
import com.stathis.data.datasource.remote.model.DepartmentResponseDto
import com.stathis.data.datasource.remote.model.ProgrammeItemDto
import com.stathis.data.datasource.remote.model.SocialItemDto
import com.stathis.data.datasource.remote.model.SyllabusItemDto
import com.stathis.model.department.DepartmentPersonnelItem
import com.stathis.model.department.DepartmentProgrammeItem
import com.stathis.model.department.DepartmentSocialItem
import com.stathis.model.department.FieldOfStudy
import com.stathis.model.department.FieldOfStudyParent
import com.stathis.model.department.Programme
import com.stathis.model.department.SocialItem

object DepartmentResponseMapper : BaseMapper<DepartmentResponseDto?, List<UiModel>> {

    override fun toDomainModel(dtoModel: DepartmentResponseDto?): List<UiModel> = listOf(
        CarouselMapper.toDomainModel(dtoModel?.carouselItems),
        FieldOfStudyParent(
            syllabusItems = dtoModel?.syllabusItems.toNotNull()
        ),
        DepartmentProgrammeItem(
            programmes = dtoModel?.programmes.toNotNull()
        ),
        DepartmentPersonnelItem(
            personnel = PersonnelMapper.toDomainModel(dtoModel?.depMembers)
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
        Programme(
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