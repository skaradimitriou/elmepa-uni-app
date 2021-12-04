package com.stathis.elmepaunivapp.callbacks

import com.stathis.elmepaunivapp.ui.main.department.model.FieldOfStudy
import com.stathis.elmepaunivapp.ui.main.department.model.Programme
import com.stathis.elmepaunivapp.ui.main.department.model.SocialChannel
import com.stathis.elmepaunivapp.ui.main.students.model.CarouselItem

interface DepartmentClickListener {
    fun openCarouselItem(data : CarouselItem)
    fun openSyllabus(data : FieldOfStudy)
    fun openSocial(data : SocialChannel)
    fun openProgrammes(data : Programme)
}