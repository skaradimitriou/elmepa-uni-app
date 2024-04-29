package com.stathis.model.professors

import com.stathis.core.base.UiModel

data class Professor(
    val fullName: String,
    val image: String,
    val email: String,
    val gender: String,
    val vocative: String
) : UiModel {
    override fun equalsContent(obj: UiModel): Boolean = when (obj) {
        is Professor -> fullName == obj.fullName && email == obj.email && gender == obj.gender && vocative == obj.vocative
        else -> false
    }
}
