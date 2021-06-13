package com.stathis.elmepaunivapp.ui.department.model

import com.stathis.elmepaunivapp.model.LocalModel

data class FieldOfStudyParent(

    val header : String,
    val list : List<FieldOfStudy>

) : LocalModel {
    override fun equalsContent(obj: LocalModel): Boolean = false
}
