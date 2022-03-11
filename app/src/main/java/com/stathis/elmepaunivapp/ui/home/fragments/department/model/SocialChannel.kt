package com.stathis.elmepaunivapp.ui.home.fragments.department.model

import com.stathis.elmepaunivapp.abstraction.LocalModel

data class SocialChannel(
    val title : String,
    val url : String,
    val imageResource : String
) : LocalModel {
    override fun equalsContent(obj: LocalModel): Boolean = when(obj){
        is SocialChannel -> title == obj.title && url == obj.url && imageResource == obj.imageResource
        else -> false
    }
}
