package com.stathis.data.remote.mapper.support

import com.stathis.common.util.toListOf
import com.stathis.common.util.toNotNull
import com.stathis.data.remote.mapper.BaseMapper
import com.stathis.data.remote.model.support.ApplicationFormItemDto
import com.stathis.model.support.ApplicationFormItem

object ApplicationFormMapper :
    BaseMapper<List<ApplicationFormItemDto>?, List<ApplicationFormItem>> {

    override fun toDomainModel(dtoModel: List<ApplicationFormItemDto>?) = dtoModel.toListOf {
        ApplicationFormItem(
            title = it.title.toNotNull(),
            openUrl = it.openUrl.toNotNull(),
        )
    }
}