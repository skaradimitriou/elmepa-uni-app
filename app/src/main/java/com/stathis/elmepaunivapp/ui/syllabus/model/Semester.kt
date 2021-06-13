package com.stathis.elmepaunivapp.ui.syllabus.model

import com.stathis.elmepaunivapp.model.LocalModel
import com.stathis.elmepaunivapp.ui.syllabus_lessons.model.Lesson

data class Semester(

    val semester: String,
    val lessonInfo: String,
    val lessons: List<Lesson>

) : LocalModel {
    override fun equalsContent(obj: LocalModel): Boolean = false
}