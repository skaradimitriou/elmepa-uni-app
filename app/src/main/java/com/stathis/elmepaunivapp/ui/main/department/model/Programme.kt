package com.stathis.elmepaunivapp.ui.main.department.model

import com.stathis.elmepaunivapp.abstraction.LocalModel

data class Programme(

    val title: String,
    val description: String,
    val imageResource: String,
    val url: String

) : LocalModel {
    override fun equalsContent(obj: LocalModel): Boolean = false
}
