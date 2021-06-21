package com.stathis.elmepaunivapp.ui.syllabus_lessons.model

import com.stathis.elmepaunivapp.abstraction.LocalModel

data class LessonHeader(

    val title : String

) : LocalModel {
    override fun equalsContent(obj: LocalModel): Boolean = false
}
