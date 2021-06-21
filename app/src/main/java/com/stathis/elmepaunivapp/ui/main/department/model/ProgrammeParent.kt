package com.stathis.elmepaunivapp.ui.main.department.model

import com.stathis.elmepaunivapp.abstraction.LocalModel

data class ProgrammeParent(

    val header : String,
    val list : List<Programme>
) : LocalModel {
    override fun equalsContent(obj: LocalModel): Boolean = false
}
