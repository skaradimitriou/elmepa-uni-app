package com.stathis.elmepaunivapp.listeners.latest

import com.stathis.elmepaunivapp.ui.students.model.UsefulLinksKt

interface StudentsScreenCallback {

    fun openSchedule()
    fun openLink(usefulLinks : UsefulLinksKt)
    fun openSyllabus(usefulLink : UsefulLinksKt)
}