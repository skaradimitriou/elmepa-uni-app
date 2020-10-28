package com.stathis.elmepaunivapp.ui.syllabus;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import com.stathis.elmepaunivapp.R;
import com.stathis.elmepaunivapp.listeners.activity_listeners.SyllabusActivityListener;
import com.stathis.elmepaunivapp.ui.syllabus.model.Semester;
import com.stathis.elmepaunivapp.ui.syllabus_lessons.SyllabusLessonsActivity;

public class SyllabusActivity extends AppCompatActivity implements SyllabusActivityListener {

    private RecyclerView recyclerView;
    private SyllabusViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syllabus);
        viewModel = new ViewModelProvider(this).get(SyllabusViewModel.class);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        viewModel.initListener(this);
        recyclerView = findViewById(R.id.syllabus_recycler);
        recyclerView.setAdapter(viewModel.adapter);
        viewModel.populateList();
    }

    @Override
    public void showLessons(Semester data) {
        startActivity(new Intent(this, SyllabusLessonsActivity.class));
    }
}
