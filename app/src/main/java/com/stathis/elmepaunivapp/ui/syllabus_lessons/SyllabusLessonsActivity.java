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
import com.stathis.elmepaunivapp.ui.syllabus_lessons.model.Lesson;

import java.util.ArrayList;

public class SyllabusLessonsActivity extends AppCompatActivity {

    SyllabusLessonsViewModel viewModel;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syllabus_lessons);
        viewModel = new ViewModelProvider(this).get(SyllabusLessonsViewModel.class);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        String jsonArray = getIntent().getExtras().getString("ARRAY");
        viewModel.jsonToLesson(jsonArray);

        recyclerView = findViewById(R.id.syllabus_lessons_recycler);
        recyclerView.setAdapter(viewModel.adapter);
        viewModel.showLessons();
    }
}