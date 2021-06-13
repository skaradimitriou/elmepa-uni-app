package com.stathis.elmepaunivapp.callbacks

import com.stathis.elmepaunivapp.ui.department.model.*

interface DepartmentClickListener {
    fun openSyllabus(data : FieldOfStudy)
    fun openVirtualTour(data : VirtualTourModel)
    fun openSocial(data : SocialChannel)
    fun openProgrammes(data : Programme)
    fun openResearch(data : Research)
}