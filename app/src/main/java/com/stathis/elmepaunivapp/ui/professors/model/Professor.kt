package com.stathis.elmepaunivapp.ui.professors.model

import com.stathis.elmepaunivapp.model.LocalModel

data class Professor(

    val fullName : String,
    val email : String,
    val gender : String,
    val vocative : String

) : LocalModel {
    override fun equalsContent(obj: LocalModel): Boolean  = false
}
