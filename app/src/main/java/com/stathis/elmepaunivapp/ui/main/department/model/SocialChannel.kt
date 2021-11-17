package com.stathis.elmepaunivapp.ui.main.department.model

import com.stathis.elmepaunivapp.abstraction.LocalModel

data class SocialChannel(
    val title : String,
    val url : String,
    val imageResource : String
) : LocalModel {
    override fun equalsContent(obj: LocalModel): Boolean = false
}
