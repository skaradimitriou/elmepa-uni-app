package com.stathis.elmepaunivapp.ui.department.model

import com.stathis.elmepaunivapp.model.LocalModel

data class SocialChannel(
    val name : String,
    val url : String,
    val image : Int
) : LocalModel {
    override fun equalsContent(obj: LocalModel): Boolean = false
}
