package com.stathis.elmepaunivapp.ui.syllabus_lessons;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.stathis.elmepaunivapp.listeners.LessonClickListener;
import com.stathis.elmepaunivapp.ui.syllabus_lessons.recycler.LessonsAdapter;
import com.stathis.elmepaunivapp.ui.syllabus_lessons.model.Lesson;

import java.util.ArrayList;

public class SyllabusLessonsViewModel extends ViewModel implements LessonClickListener {

    ArrayList<Lesson> lessons = new ArrayList<>();
    LessonsAdapter adapter = new LessonsAdapter(this);

    public void showLessons(){
        adapter.submitList(lessons);
    }

    public void jsonToLesson(String jsonString){
        JsonArray array = new Gson().fromJson(jsonString, JsonArray.class);

        for (int i = 0; i < array.size(); i++) {
            JsonElement item = array.get(i);
            Lesson lesson = new Gson().fromJson(item,Lesson.class);
            lessons.add(lesson);
        }

        Log.d("ARRAY",lessons.toString());
    }

    @Override
    public void onLessonClick(Lesson lesson) {
        Log.d("hello",lesson.toString());
    }
}
