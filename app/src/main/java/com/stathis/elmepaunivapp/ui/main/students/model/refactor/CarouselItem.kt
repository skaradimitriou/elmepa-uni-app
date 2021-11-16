package com.stathis.elmepaunivapp.ui.main.students.model.refactor

import com.stathis.elmepaunivapp.abstraction.LocalModel

data class CarouselItem(val title : String, val url : String,val imageResource : String,val position : Int) : LocalModel {
    override fun equalsContent(obj: LocalModel): Boolean = false
}
