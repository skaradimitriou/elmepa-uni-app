package com.stathis.data.remote.mapper.personnel

import com.stathis.common.util.toNotNull
import com.stathis.data.remote.mapper.BaseMapper
import com.stathis.data.remote.model.personnel.PersonnelDto
import com.stathis.model.personnel.Person

object PersonnelMapper : BaseMapper<List<PersonnelDto>?, List<Person>> {

    override fun toDomainModel(dtoModel: List<PersonnelDto>?) = dtoModel?.map {
        it.toDomainModel()
    } ?: listOf()

    private fun PersonnelDto?.toDomainModel() = Person(
        fullName = this?.fullName.toNotNull(),
        description = this?.description.toNotNull(),
        image = this?.image.toNotNull(),
        email = this?.email.toNotNull(),
        gender = this?.gender.toNotNull(),
        vocative = this?.vocative.toNotNull()
    )
}

