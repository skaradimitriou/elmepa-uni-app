package com.stathis.elmepaunivapp.callbacks

import com.stathis.elmepaunivapp.model.syllabus.Semester

interface SyllabusClickListener {
    fun onSemesterTap(syllabus : Semester)
}