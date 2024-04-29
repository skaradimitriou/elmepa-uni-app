package com.stathis.data.datasource.remote.mapper

import com.stathis.core.util.toListOf
import com.stathis.core.util.toNotNull
import com.stathis.data.datasource.remote.model.SemesterDto
import com.stathis.model.syllabus.Semester

object SemesterMapper : BaseMapper<List<SemesterDto>?, List<Semester>> {

    override fun toDomainModel(dtoModel: List<SemesterDto>?) = dtoModel.toListOf {
        Semester(name = it.name.toNotNull())
    }
}