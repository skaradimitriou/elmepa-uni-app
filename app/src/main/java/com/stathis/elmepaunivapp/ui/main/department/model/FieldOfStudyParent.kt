package com.stathis.elmepaunivapp.ui.main.department.model

import com.stathis.elmepaunivapp.abstraction.LocalModel

data class FieldOfStudyParent(

    val header : String,
    val list : List<FieldOfStudy>

) : LocalModel {
    override fun equalsContent(obj: LocalModel): Boolean = false
}
