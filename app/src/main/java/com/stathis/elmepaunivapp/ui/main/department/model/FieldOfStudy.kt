package com.stathis.elmepaunivapp.ui.main.department.model

import com.stathis.elmepaunivapp.model.LocalModel

data class FieldOfStudy(

    val name : String,
    val direction : String,
    val image : Int

) : LocalModel {
    override fun equalsContent(obj: LocalModel): Boolean  = false
}
