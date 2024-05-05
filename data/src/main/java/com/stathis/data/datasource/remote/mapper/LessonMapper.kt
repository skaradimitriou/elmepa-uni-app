package com.stathis.data.datasource.remote.mapper

import com.stathis.core.util.toNotNull
import com.stathis.data.datasource.remote.model.LessonDto
import com.stathis.model.syllabus.Lesson
import com.stathis.model.syllabus.OrientationType

object LessonMapper : BaseMapper<LessonDto?, Lesson> {

    override fun toDomainModel(dtoModel: LessonDto?) = Lesson(
        name = dtoModel?.name.toNotNull(),
        description = dtoModel?.description.toNotNull(),
        hours = dtoModel?.hours.toNotNull(),
        mandatory = dtoModel?.mandatory.toNotNull(),
        orientation = dtoModel?.orientation.toListOfOrientations(),
        semester = dtoModel?.semester.toNotNull()
    )

    private fun List<String?>?.toListOfOrientations() = this?.map {
        OrientationType.valueOf(it ?: OrientationType.UNDEFINED.name)
    }.toNotNull()
}