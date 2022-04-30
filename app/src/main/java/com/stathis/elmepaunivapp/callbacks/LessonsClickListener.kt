package com.stathis.elmepaunivapp.callbacks

import com.stathis.elmepaunivapp.ui.lessons.model.Lesson

interface LessonsClickListener {
    fun onLessonTap(lesson : Lesson)
}