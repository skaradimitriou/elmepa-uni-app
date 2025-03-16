package com.elmepa.database.ext

import com.elmepa.database.model.ApplicationFormEntity
import com.elmepa.support.model.ApplicationForm

fun ApplicationForm.toEntity() = ApplicationFormEntity(
    title = title,
    openUrl = openUrl
)

fun ApplicationFormEntity.toApplicationForm() = ApplicationForm(
    title = title,
    openUrl = openUrl
)
