package com.stathis.elmepaunivapp.ui.syllabus;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.util.Log;

import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.stathis.elmepaunivapp.R;
import com.stathis.elmepaunivapp.abstraction.AbstractActivity;
import com.stathis.elmepaunivapp.listeners.activity_listeners.SyllabusActivityListener;
import com.stathis.elmepaunivapp.ui.syllabus.model.Semester;
import com.stathis.elmepaunivapp.ui.syllabus_lessons.SyllabusLessonsActivity;

public class SyllabusActivity extends AbstractActivity implements SyllabusActivityListener {

    private TabLayout tabLayout;
    private RecyclerView recyclerView;
    private SyllabusViewModel viewModel;

    public SyllabusActivity() {
        super(R.layout.activity_syllabus);
    }

    @Override
    public void initial() {
        viewModel = new ViewModelProvider(this).get(SyllabusViewModel.class);

        recyclerView = findViewById(R.id.syllabus_recycler);
        tabLayout = findViewById(R.id.tabLayout);
    }

    @Override
    public void running() {
        int userChoice = getIntent().getIntExtra("userTabChoice", 0);

        viewModel.initListener(this);
        recyclerView.setAdapter(viewModel.adapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0: {
                        viewModel.getDataList(getApplicationContext());
                        viewModel.adapter.notifyDataSetChanged();
                        break;
                    }
                    case 1: {
                        viewModel.getBusinessAdministrationList(getApplicationContext());
                        viewModel.adapter.notifyDataSetChanged();
                        break;
                    }
                    case 2: {
                        viewModel.getMarketingList(getApplicationContext());
                        viewModel.adapter.notifyDataSetChanged();
                        break;
                    }
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });

        tabLayout.getTabAt(userChoice).select();
        viewModel.getDataList(getApplicationContext());
    }

    @Override
    public void stopped() {}

    @Override
    public void showLessons(Semester data) {
        String jsonArray = new Gson().toJson(data.getLessons());
        Log.d("JSON_ARRAY", jsonArray);
        startActivity(new Intent(this, SyllabusLessonsActivity.class)
                .putExtra("ARRAY", jsonArray)
                .putExtra("LESSONS_INFO", data.getLessonInfo()));
    }
}
