package com.stathis.elmepaunivapp.ui.syllabus_lessons.model

import com.stathis.elmepaunivapp.abstraction.LocalModel

data class LessonHeader(

    val title : String

) : LocalModel {
    override fun equalsContent(obj: LocalModel): Boolean = when(obj){
        is LessonHeader -> title == obj.title
        else -> false
    }
}
