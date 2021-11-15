package com.stathis.elmepaunivapp.ui.main.students.model.refactor

import com.stathis.elmepaunivapp.abstraction.LocalModel

data class StudentResponse(
    val carouselItems : List<CarouselItem>,
    val syllabusItems : List<SyllabusItem>,
    val links : List<LinkItem>
) : LocalModel {
    override fun equalsContent(obj: LocalModel): Boolean = false
}
