package com.stathis.elmepaunivapp.ui.students.model

import com.stathis.elmepaunivapp.abstraction.LocalModel

data class CarouselItem(val title : String, val url : String,val imageResource : String,val position : Int) : LocalModel {
    override fun equalsContent(obj: LocalModel): Boolean = when(obj){
        is CarouselItem -> title == obj.title && url == obj.url && imageResource == obj.imageResource && position == obj.position
        else -> false
    }
}
