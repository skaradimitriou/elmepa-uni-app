package com.stathis.elmepaunivapp.callbacks

import com.stathis.elmepaunivapp.ui.home.fragments.students.model.CarouselItem
import com.stathis.elmepaunivapp.ui.home.fragments.students.model.LinkItem
import com.stathis.elmepaunivapp.ui.home.fragments.students.model.SyllabusItem

interface StudentsScreenCallback {
    fun openCarouselItem(item : CarouselItem)
    fun openLink(item : LinkItem)
    fun openSyllabus(item : SyllabusItem)
}