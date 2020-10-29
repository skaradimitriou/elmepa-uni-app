package com.stathis.elmepaunivapp.listeners;

import android.view.View;

import com.stathis.elmepaunivapp.ui.syllabus_lessons.model.Lesson;

public interface LessonClickListener extends View.OnClickListener {

    void onLessonClick(Lesson lesson);
}
