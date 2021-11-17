package com.stathis.elmepaunivapp.ui.main.department.model

import com.stathis.elmepaunivapp.abstraction.LocalModel

data class DepMember(

    val name : String,
    val img : String

) : LocalModel {
    override fun equalsContent(obj: LocalModel): Boolean = false
}
