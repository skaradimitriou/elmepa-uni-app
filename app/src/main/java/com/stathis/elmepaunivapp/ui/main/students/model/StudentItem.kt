package com.stathis.elmepaunivapp.ui.main.students.model

import com.stathis.elmepaunivapp.model.LocalModel

data class StudentItem(

    val headerTxt : String,
    val list : List<UsefulLinks>

) : LocalModel {
    override fun equalsContent(obj: LocalModel): Boolean = false
}
