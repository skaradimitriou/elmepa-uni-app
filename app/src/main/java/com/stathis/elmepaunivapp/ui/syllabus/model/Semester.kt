package com.stathis.elmepaunivapp.ui.syllabus.model

import com.stathis.elmepaunivapp.model.LocalModel
import com.stathis.elmepaunivapp.ui.syllabus_lessons.model.LessonKt

data class Semester(

    val semester: String,
    val lessonInfo: String,
    val lessons: List<LessonKt>

) : LocalModel {
    override fun equalsContent(obj: LocalModel): Boolean = false
}