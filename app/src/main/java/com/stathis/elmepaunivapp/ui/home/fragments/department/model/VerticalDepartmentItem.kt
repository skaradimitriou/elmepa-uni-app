package com.stathis.elmepaunivapp.ui.home.fragments.department.model

import com.stathis.elmepaunivapp.abstraction.LocalModel

data class VerticalDepartmentItem(val title : String, val list : List<LocalModel>) : LocalModel {
    override fun equalsContent(obj: LocalModel): Boolean = when(obj){
        is VerticalDepartmentItem -> title == obj.title && list == obj.list
        else -> false
    }
}