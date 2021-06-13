package com.stathis.elmepaunivapp.ui.department.model

import com.stathis.elmepaunivapp.model.LocalModel

data class DepMember(

    val name : String,
    val image : Int

) : LocalModel {
    override fun equalsContent(obj: LocalModel): Boolean = false
}
