package com.stathis.model.department

import com.stathis.core.base.UiModel

data class DepartmentPersonnelItem(
    val personnel: List<UiModel>
) : UiModel {
    override fun equalsContent(obj: UiModel) = when (obj) {
        is DepartmentPersonnelItem -> personnel == obj.personnel
        else -> false
    }
}