package com.stathis.elmepaunivapp.ui.students.model

import com.stathis.elmepaunivapp.model.LocalModel

data class StudentItemKt(

    val headerTxt : String,
    val list : List<UsefulLinksKt>

) : LocalModel {
    override fun equalsContent(obj: LocalModel): Boolean = false
}
