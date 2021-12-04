package com.stathis.elmepaunivapp.ui.main.department.model

import com.stathis.elmepaunivapp.abstraction.LocalModel

data class NewDepartmentItem(val title : String, val list : List<LocalModel>) : LocalModel {
    override fun equalsContent(obj: LocalModel): Boolean = when(obj){
        is NewDepartmentItem -> title == obj.title && list == obj.list
        else -> false
    }
}

