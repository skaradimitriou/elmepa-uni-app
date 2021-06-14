package com.stathis.elmepaunivapp.ui.main.students.model

import com.stathis.elmepaunivapp.model.LocalModel

data class Schedule(

    val mainTxt : String,
    val mainButton : String

) : LocalModel {
    override fun equalsContent(obj: LocalModel): Boolean = false
}
