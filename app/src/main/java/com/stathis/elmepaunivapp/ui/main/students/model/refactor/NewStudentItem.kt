package com.stathis.elmepaunivapp.ui.main.students.model.refactor

import com.stathis.elmepaunivapp.abstraction.LocalModel

data class NewStudentItem(val title : String, val list : List<LocalModel>) : LocalModel{
    override fun equalsContent(obj: LocalModel): Boolean = false
}
