package com.stathis.elmepaunivapp.ui.department.model

import com.stathis.elmepaunivapp.model.LocalModel

data class ProgrammeParent(

    val header : String,
    val list : List<Programme>
) : LocalModel {
    override fun equalsContent(obj: LocalModel): Boolean = false
}
