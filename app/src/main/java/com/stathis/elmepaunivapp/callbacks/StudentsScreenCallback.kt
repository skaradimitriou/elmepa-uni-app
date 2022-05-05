package com.stathis.elmepaunivapp.callbacks

import com.stathis.elmepaunivapp.ui.students.model.CarouselItem
import com.stathis.elmepaunivapp.ui.students.model.LinkItem
import com.stathis.elmepaunivapp.ui.students.model.SyllabusItem

interface StudentsScreenCallback {
    fun openCarouselItem(item : CarouselItem)
    fun openLink(item : LinkItem)
}