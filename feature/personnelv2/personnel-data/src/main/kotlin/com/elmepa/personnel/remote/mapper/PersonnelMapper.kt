package com.elmepa.personnel.remote.mapper

import com.elmepa.personnel.model.Gender
import com.elmepa.personnel.model.Person
import com.elmepa.personnel.remote.model.PersonnelDto
import com.stathis.data.remote.mapper.BaseMapper

internal object PersonnelMapper : BaseMapper<List<PersonnelDto?>?, List<Person>> {

    override fun toDomainModel(dtoModel: List<PersonnelDto?>?) = dtoModel?.map {
        it.toDomainModel()
    } ?: listOf()

    private fun PersonnelDto?.toDomainModel() = Person(
        fullName = this?.fullName.orEmpty(),
        description = this?.description.orEmpty(),
        image = this?.image.orEmpty(),
        email = this?.email.orEmpty(),
        gender = this?.gender.toGender(),
        vocative = this?.vocative.orEmpty()
    )

    private fun String?.toGender() = when (this) {
        "male" -> Gender.MALE
        "female" -> Gender.FEMALE
        else -> Gender.FEMALE
    }
}

