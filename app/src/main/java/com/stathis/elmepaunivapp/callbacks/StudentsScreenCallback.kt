package com.stathis.elmepaunivapp.callbacks

import com.stathis.elmepaunivapp.ui.students.model.UsefulLinks

interface StudentsScreenCallback {

    fun openSchedule()
    fun openLink(usefulLinks : UsefulLinks)
    fun openSyllabus(usefulLink : UsefulLinks)
}