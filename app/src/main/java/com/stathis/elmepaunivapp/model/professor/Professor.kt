package com.stathis.elmepaunivapp.model.professor

import com.stathis.elmepaunivapp.abstraction.LocalModel

data class Professor(

    val fullName : String,
    val email : String,
    val gender : String,
    val vocative : String

) : LocalModel {
    override fun equalsContent(obj: LocalModel): Boolean  = when(obj){
        is Professor -> fullName == obj.fullName && email == obj.email && gender == obj.gender && vocative == obj.vocative
        else -> false
    }
}
