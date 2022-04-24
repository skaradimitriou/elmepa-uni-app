package com.stathis.elmepaunivapp.ui.home.department.model

import com.stathis.elmepaunivapp.abstraction.LocalModel

data class FieldOfStudy(

    val title : String,
    val tabNo : Int,
    val imageResource : String

) : LocalModel {
    override fun equalsContent(obj: LocalModel): Boolean  = when(obj){
        is FieldOfStudy -> title == obj.title && tabNo == obj.tabNo && imageResource == obj.imageResource
        else -> false
    }
}
