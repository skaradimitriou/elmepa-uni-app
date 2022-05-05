package com.stathis.elmepaunivapp.ui.students.model

import com.stathis.elmepaunivapp.abstraction.LocalModel

data class StudentResponse(
    val carouselItems : List<CarouselItem>,
    val links : List<LinkItem>
) : LocalModel {
    override fun equalsContent(obj: LocalModel): Boolean = when(obj){
        is StudentResponse -> carouselItems == obj.carouselItems && links == obj.links
        else -> false
    }
}
