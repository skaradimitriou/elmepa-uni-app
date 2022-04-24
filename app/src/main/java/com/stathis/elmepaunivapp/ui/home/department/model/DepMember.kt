package com.stathis.elmepaunivapp.ui.home.department.model

import com.stathis.elmepaunivapp.abstraction.LocalModel

data class DepMember(

    val name : String,
    val img : String

) : LocalModel {
    override fun equalsContent(obj: LocalModel): Boolean = when(obj){
        is DepMember -> name == obj.name && img == obj.img
        else -> false
    }
}
