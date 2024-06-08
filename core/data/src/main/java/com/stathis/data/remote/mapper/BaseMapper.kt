package com.stathis.data.remote.mapper

interface BaseMapper<DtoModel, DomainModel> {

    fun toDomainModel(dtoModel: DtoModel?): DomainModel
}