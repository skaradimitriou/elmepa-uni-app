package com.stathis.elmepaunivapp.callbacks

import com.stathis.elmepaunivapp.ui.main.students.model.UsefulLinks

interface StudentsScreenCallback {

    fun openSchedule()
    fun openLink(usefulLinks : UsefulLinks)
    fun openSyllabus(usefulLink : UsefulLinks)
}