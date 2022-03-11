package com.stathis.elmepaunivapp.ui.home.fragments.students.model

import com.stathis.elmepaunivapp.abstraction.LocalModel

data class LinkItem(val imageResource : String,val title : String, val url : String) : LocalModel {
    override fun equalsContent(obj: LocalModel): Boolean = when(obj){
        is LinkItem -> imageResource == obj.imageResource && title == obj.title && url == obj.url
        else -> false
    }
}
