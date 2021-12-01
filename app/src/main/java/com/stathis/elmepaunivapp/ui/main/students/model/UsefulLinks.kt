package com.stathis.elmepaunivapp.ui.main.students.model

import com.stathis.elmepaunivapp.abstraction.LocalModel

data class UsefulLinks(

    val name : String,
    val url : String,
    val imageResource : String

) : LocalModel {
    override fun equalsContent(obj: LocalModel): Boolean = false
}
