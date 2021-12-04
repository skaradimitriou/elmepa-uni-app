package com.stathis.elmepaunivapp.ui.main.students.model

import com.stathis.elmepaunivapp.abstraction.LocalModel

data class SyllabusItem(val title : String, val imageResource : String, val tabNo : Int) : LocalModel {
    override fun equalsContent(obj: LocalModel): Boolean = when(obj){
        is SyllabusItem -> title == obj.title && imageResource == obj.imageResource && tabNo == obj.tabNo
        else -> false
    }
}
