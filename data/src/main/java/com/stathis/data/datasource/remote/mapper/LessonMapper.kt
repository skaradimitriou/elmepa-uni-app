package com.stathis.data.datasource.remote.mapper

import com.stathis.core.util.toNotNull
import com.stathis.data.datasource.remote.model.LessonDto
import com.stathis.model.syllabus.Lesson
import com.stathis.model.syllabus.OrientationType

object LessonMapper : BaseMapper<List<LessonDto>?, List<Lesson>> {

    override fun toDomainModel(dtoModel: List<LessonDto>?) = dtoModel?.map {
        Lesson(
            name = it.name.toNotNull(),
            description = it.description.toNotNull(),
            hours = it.hours.toNotNull(),
            mandatory = it.mandatory.toNotNull(),
            orientation = it.orientation.toListOfOrientations(),
            semester = it.semester.toNotNull()
        )
    }.toNotNull().sortedBy { !it.mandatory }

    private fun List<String?>?.toListOfOrientations() = this?.map {
        OrientationType.valueOf(it ?: OrientationType.UNDEFINED.name)
    }.toNotNull()
}