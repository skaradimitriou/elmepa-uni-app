package com.stathis.elmepaunivapp.ui.home.fragments.students.model

import com.stathis.elmepaunivapp.abstraction.LocalModel

data class CarouselParent(val list : List<CarouselItem>) : LocalModel{
    override fun equalsContent(obj: LocalModel): Boolean = when(obj){
        is CarouselParent -> list == obj.list
        else -> false
    }
}
