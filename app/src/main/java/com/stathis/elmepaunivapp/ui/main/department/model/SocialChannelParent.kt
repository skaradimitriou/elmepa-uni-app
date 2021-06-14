package com.stathis.elmepaunivapp.ui.main.department.model

import com.stathis.elmepaunivapp.model.LocalModel

data class SocialChannelParent(

    val header : String,
    val list : List<SocialChannel>
) : LocalModel {
    override fun equalsContent(obj: LocalModel): Boolean = false
}
