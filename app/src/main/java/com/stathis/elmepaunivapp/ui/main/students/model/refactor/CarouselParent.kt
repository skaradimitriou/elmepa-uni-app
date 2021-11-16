package com.stathis.elmepaunivapp.ui.main.students.model.refactor

import com.stathis.elmepaunivapp.abstraction.LocalModel

data class CarouselParent(val list : List<CarouselItem>) : LocalModel{
    override fun equalsContent(obj: LocalModel): Boolean = false
}
