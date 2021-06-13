package com.stathis.elmepaunivapp.ui.department.model

import com.stathis.elmepaunivapp.model.LocalModel

data class DepMemberParent(

    val header : String,
    val list : List<DepMember>
) : LocalModel {
    override fun equalsContent(obj: LocalModel): Boolean = false
}
