package com.stathis.elmepaunivapp.recyclerview;

import android.view.View;

import com.stathis.elmepaunivapp.models.Lesson;

public interface LessonClickListener extends View.OnClickListener {

    void onLessonClick(Lesson lesson);
}
