package com.stathis.data.datasource.remote.mapper

import com.stathis.core.util.toNotNull
import com.stathis.data.datasource.remote.model.LessonDto
import com.stathis.model.syllabus.Lesson

object LessonListMapper : BaseMapper<List<LessonDto>?, List<Lesson>> {

    override fun toDomainModel(dtoModel: List<LessonDto>?) = dtoModel?.map {
        LessonMapper.toDomainModel(it)
    }.toNotNull().sortedBy { !it.mandatory }
}