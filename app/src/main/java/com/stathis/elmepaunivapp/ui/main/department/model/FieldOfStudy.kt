package com.stathis.elmepaunivapp.ui.main.department.model

import com.stathis.elmepaunivapp.abstraction.LocalModel

data class FieldOfStudy(

    val title : String,
    val tabNo : Int,
    val imageResource : String

) : LocalModel {
    override fun equalsContent(obj: LocalModel): Boolean  = false
}
