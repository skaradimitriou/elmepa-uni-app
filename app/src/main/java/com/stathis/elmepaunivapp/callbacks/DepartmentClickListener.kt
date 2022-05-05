package com.stathis.elmepaunivapp.callbacks

import com.stathis.elmepaunivapp.ui.department.model.Programme
import com.stathis.elmepaunivapp.ui.department.model.SocialChannel
import com.stathis.elmepaunivapp.ui.students.model.CarouselItem

interface DepartmentClickListener {
    fun openCarouselItem(data : CarouselItem)
    fun openSocial(data : SocialChannel)
    fun openProgrammes(data : Programme)
}