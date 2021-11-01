package com.stathis.elmepaunivapp.ui.syllabus_lessons.model

import com.stathis.elmepaunivapp.abstraction.LocalModel

data class Lesson(

    var name: String,
    val mandatory: String,
    val description: String,
    val direction: String

) : LocalModel {
    override fun equalsContent(obj: LocalModel): Boolean = false
}
