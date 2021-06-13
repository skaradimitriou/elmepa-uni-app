package com.stathis.elmepaunivapp.ui.students.model

import com.stathis.elmepaunivapp.model.LocalModel

data class UsefulLinks(

    val name : String,
    val url : String,
    val imageResource : Int

) : LocalModel {
    override fun equalsContent(obj: LocalModel): Boolean = false
}
