package com.stathis.elmepaunivapp.ui.syllabus_lessons;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.stathis.elmepaunivapp.R;
import com.stathis.elmepaunivapp.abstraction.AbstractActivity;
import com.stathis.elmepaunivapp.ui.syllabus_lessons.model.Lesson;

import java.util.ArrayList;

public class SyllabusLessonsActivity extends AbstractActivity {

    SyllabusLessonsViewModel viewModel;
    RecyclerView recyclerView;
    String jsonArray, lessonsInfo;

    public SyllabusLessonsActivity() {
        super(R.layout.activity_syllabus_lessons);
    }

    @Override
    public void initial() {
        viewModel = new ViewModelProvider(this).get(SyllabusLessonsViewModel.class);

        recyclerView = findViewById(R.id.syllabus_lessons_recycler);
    }

    @Override
    public void running() {
        lessonsInfo = getIntent().getExtras().getString("LESSONS_INFO");
        Log.d("info", lessonsInfo);

        viewModel.addTitle(lessonsInfo);

        jsonArray = getIntent().getExtras().getString("ARRAY");
        viewModel.jsonToLesson(jsonArray);

        recyclerView.setAdapter(viewModel.adapter);
        viewModel.showLessons();
    }

    @Override
    public void stopped() {}
}