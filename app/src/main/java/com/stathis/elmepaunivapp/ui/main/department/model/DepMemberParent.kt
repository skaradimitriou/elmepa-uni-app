package com.stathis.elmepaunivapp.ui.main.department.model

import com.stathis.elmepaunivapp.abstraction.LocalModel

data class DepMemberParent(

    val header : String,
    val list : List<DepMember>
) : LocalModel {
    override fun equalsContent(obj: LocalModel): Boolean = false
}
