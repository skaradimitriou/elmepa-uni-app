package com.stathis.elmepaunivapp.ui.department.model

import com.stathis.elmepaunivapp.abstraction.LocalModel

data class Programme(

    val title: String,
    val description: String,
    val imageResource: String,
    val url: String

) : LocalModel {
    override fun equalsContent(obj: LocalModel): Boolean = when(obj){
        is Programme -> title == obj.title && description == obj.description && imageResource == obj.imageResource && url == obj.url
        else -> false
    }
}
