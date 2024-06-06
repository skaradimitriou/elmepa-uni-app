package com.stathis.data.datasource.remote.mapper.syllabus

import com.stathis.common.util.toNotNull
import com.stathis.data.datasource.remote.mapper.EnhancedBaseMapper
import com.stathis.data.datasource.remote.model.LessonDto
import com.stathis.data.datasource.remote.model.OrientationModelDto
import com.stathis.model.syllabus.Lesson
import com.stathis.model.syllabus.OrientationType

object LessonListMapper : EnhancedBaseMapper<List<LessonDto>?, List<Lesson>> {

    override fun toDomainModel(dtoModel: List<LessonDto>?, vararg args: Any?): List<Lesson> {
        val orientationName = args.getOrNull(0) as? String ?: OrientationType.UNDEFINED.name

        return dtoModel?.map {
            it.toDomainModel(orientation = orientationName)
        }.toNotNull()
            .sortedBy { !it.mandatory }
    }

    private fun LessonDto?.toDomainModel(orientation: String) = Lesson(
        name = this?.name.toNotNull(),
        description = this?.description.toNotNull(),
        hours = this?.hours.toNotNull(),
        orientation = this?.orientation.toListOfOrientations(),
        mandatory = this?.orientation.defineIfItsMandatory(orientation),
        semester = this?.semester.toNotNull()
    )

    private fun List<OrientationModelDto?>?.toListOfOrientations() = this?.map {
        OrientationType.valueOf(it?.type ?: OrientationType.UNDEFINED.name)
    }.toNotNull()

    private fun List<OrientationModelDto>?.defineIfItsMandatory(orientation: String) =
        this?.find { it.type == orientation }?.mandatory ?: false
}
