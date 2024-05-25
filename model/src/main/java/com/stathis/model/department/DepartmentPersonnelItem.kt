package com.stathis.model.department

import com.stathis.model.UiModel

data class DepartmentPersonnelItem(
    val personnel: List<UiModel>
) : UiModel {
    override fun equalsContent(obj: UiModel) = when (obj) {
        is DepartmentPersonnelItem -> personnel == obj.personnel
        else -> false
    }
}