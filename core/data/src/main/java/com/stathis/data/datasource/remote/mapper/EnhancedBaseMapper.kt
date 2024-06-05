package com.stathis.data.datasource.remote.mapper

interface EnhancedBaseMapper<DtoModel, DomainModel> {

    fun toDomainModel(dtoModel: DtoModel?, vararg args: Any?): DomainModel
}