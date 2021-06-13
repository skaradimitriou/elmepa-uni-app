package com.stathis.elmepaunivapp.callbacks

import com.stathis.elmepaunivapp.ui.syllabus_lessons.model.Lesson

interface LessonsClickListener {

    fun onLessonTap(lesson : Lesson)
}