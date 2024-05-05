package com.stathis.data.datasource.remote.mapper

import com.stathis.core.util.toNotNull
import com.stathis.data.datasource.remote.model.ResearchItemDto
import com.stathis.data.datasource.remote.model.ResearchResponseDto
import com.stathis.model.research.ResearchItem
import com.stathis.model.research.ResearchResponse

object ResearchMapper : BaseMapper<List<ResearchResponseDto>?, List<ResearchResponse>> {

    override fun toDomainModel(dtoModel: List<ResearchResponseDto>?): List<ResearchResponse> {
        return dtoModel?.map {
            it.toNotNull()
        }.toNotNull()
    }

    private fun ResearchResponseDto?.toNotNull() = ResearchResponse(
        categoryName = this?.categoryName.toNotNull(),
        researchItems = this?.researchItems.toNotNull()
    )

    private fun List<ResearchItemDto>?.toNotNull() = this?.map {
        it.toNotNull()
    }.toNotNull()

    private fun ResearchItemDto?.toNotNull() = ResearchItem(
        name = this?.name.toNotNull(),
        openUrl = this?.url.toNotNull(),
        imageUrl = this?.imageResource.toNotNull()
    )
}