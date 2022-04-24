package com.stathis.elmepaunivapp.callbacks

import com.stathis.elmepaunivapp.ui.home.students.model.CarouselItem
import com.stathis.elmepaunivapp.ui.home.students.model.LinkItem
import com.stathis.elmepaunivapp.ui.home.students.model.SyllabusItem

interface StudentsScreenCallback {
    fun openCarouselItem(item : CarouselItem)
    fun openLink(item : LinkItem)
    fun openSyllabus(item : SyllabusItem)
}