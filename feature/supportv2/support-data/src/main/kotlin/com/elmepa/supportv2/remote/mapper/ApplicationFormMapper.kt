package com.elmepa.supportv2.remote.mapper

import com.elmepa.supportv2.model.ApplicationForm
import com.elmepa.supportv2.remote.model.ApplicationFormDto
import com.stathis.common.util.toListOf
import com.stathis.common.util.toNotNull
import com.stathis.data.remote.mapper.BaseMapper

internal object ApplicationFormMapper : BaseMapper<List<ApplicationFormDto>?, List<ApplicationForm>> {

    override fun toDomainModel(dtoModel: List<ApplicationFormDto>?) = dtoModel.toListOf {
        ApplicationForm(
            title = it.title.toNotNull(),
            openUrl = it.openUrl.toNotNull(),
        )
    }
}
