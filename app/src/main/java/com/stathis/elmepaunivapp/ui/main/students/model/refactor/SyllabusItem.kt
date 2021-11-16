package com.stathis.elmepaunivapp.ui.main.students.model.refactor

import com.stathis.elmepaunivapp.abstraction.LocalModel

data class SyllabusItem(val title : String, val imageResource : String, val tabNo : Int) : LocalModel {
    override fun equalsContent(obj: LocalModel): Boolean = false
}
