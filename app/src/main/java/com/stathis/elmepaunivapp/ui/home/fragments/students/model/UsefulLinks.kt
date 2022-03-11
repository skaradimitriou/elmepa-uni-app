package com.stathis.elmepaunivapp.ui.home.fragments.students.model

import com.stathis.elmepaunivapp.abstraction.LocalModel

data class UsefulLinks(

    val name : String,
    val url : String,
    val imageResource : String

) : LocalModel {
    override fun equalsContent(obj: LocalModel): Boolean = when(obj){
        is UsefulLinks -> name == obj.name && url == obj.url && imageResource == obj.imageResource
        else -> false
    }
}
