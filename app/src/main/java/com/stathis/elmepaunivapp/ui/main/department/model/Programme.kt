package com.stathis.elmepaunivapp.ui.main.department.model

import com.stathis.elmepaunivapp.abstraction.LocalModel

data class Programme(

    val name: String,
    val description: String,
    val image: Int,
    val url: String

) : LocalModel {
    override fun equalsContent(obj: LocalModel): Boolean = false
}
