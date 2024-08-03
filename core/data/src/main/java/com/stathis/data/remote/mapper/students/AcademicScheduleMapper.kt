package com.stathis.data.remote.mapper.students

import com.stathis.common.util.toListOf
import com.stathis.common.util.toNotNull
import com.stathis.data.remote.mapper.BaseMapper
import com.stathis.data.remote.model.students.AcademicScheduleItemDto
import com.stathis.model.UiModel
import com.stathis.model.students.AcademicScheduleEntry
import com.stathis.model.students.AcademicScheduleTitle

object AcademicScheduleMapper : BaseMapper<List<AcademicScheduleItemDto?>, List<UiModel>> {

    override fun toDomainModel(dtoModel: List<AcademicScheduleItemDto?>?) =
        dtoModel?.filter {
            // has either title or entry text
            it?.title?.isNotEmpty().toNotNull() || it?.entry?.isNotEmpty().toNotNull()
        }.toListOf { dto ->
            if (dto?.isBoldTitle.toNotNull()) {
                dto.toScheduleTitleItem()
            } else {
                dto.toScheduleEntryItem()
            }
        }.drop(1) // drop first element

    private fun AcademicScheduleItemDto?.toScheduleTitleItem() = AcademicScheduleTitle(
        title = this?.title.toNotNull()
    )

    private fun AcademicScheduleItemDto?.toScheduleEntryItem(): AcademicScheduleEntry {
        val fullDate = if (this?.startDate.toNotNull().isEmpty()) {
            this?.endDate.toNotNull().replace("-", "/")
        } else {
            val startDate = this?.startDate.toNotNull().replace("-", "/")
            val endDate = this?.endDate.toNotNull().replace("-", "/")
            "$startDate - $endDate"
        }
        return AcademicScheduleEntry(title = this?.entry.toNotNull(), date = fullDate)
    }
}