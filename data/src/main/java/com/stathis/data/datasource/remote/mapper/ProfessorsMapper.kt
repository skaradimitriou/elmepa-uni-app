package com.stathis.data.datasource.remote.mapper

import com.stathis.data.datasource.remote.model.ProfessorDto
import com.stathis.model.professors.Professor

object ProfessorsMapper : BaseMapper<List<ProfessorDto>?, List<Professor>> {

    override fun toDomainModel(dtoModel: List<ProfessorDto>?) = dtoModel?.map {
        it.toDomainModel()
    } ?: listOf()

    private fun ProfessorDto?.toDomainModel() = Professor(
        fullName = this?.fullName ?: "",
        image = this?.image ?: "",
        email = this?.email ?: "",
        gender = this?.gender ?: "",
        vocative = this?.vocative ?: "",
    )
}

