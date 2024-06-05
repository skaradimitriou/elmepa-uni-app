package com.stathis.data.datasource.remote.mapper

interface BaseMapper<DtoModel, DomainModel> {

    fun toDomainModel(dtoModel: DtoModel?): DomainModel
}