package com.stathis.elmepaunivapp.callbacks

import com.stathis.elmepaunivapp.ui.main.students.model.refactor.CarouselItem
import com.stathis.elmepaunivapp.ui.main.students.model.refactor.LinkItem
import com.stathis.elmepaunivapp.ui.main.students.model.refactor.SyllabusItem

interface StudentsScreenCallback {

    fun openCarouselItem(item : CarouselItem)
    fun openLink(item : LinkItem)
    fun openSyllabus(item : SyllabusItem)

}