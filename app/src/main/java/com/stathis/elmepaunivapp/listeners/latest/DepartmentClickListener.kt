package com.stathis.elmepaunivapp.listeners.latest

import com.stathis.elmepaunivapp.ui.department.model.*

interface DepartmentClickListener {
    fun openSyllabus(data : FieldOfStudy)
    fun openVirtualTour(data : VirtualTourModel)
    fun openSocial(data : SocialChannel)
    fun openProgrammes(data : Programme)
    fun openResearch(data : ResearchKt)
}