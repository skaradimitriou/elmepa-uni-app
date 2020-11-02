package com.stathis.elmepaunivapp.ui.syllabus;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;

import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.stathis.elmepaunivapp.R;
import com.stathis.elmepaunivapp.listeners.activity_listeners.SyllabusActivityListener;
import com.stathis.elmepaunivapp.ui.syllabus.model.Semester;
import com.stathis.elmepaunivapp.ui.syllabus_lessons.SyllabusLessonsActivity;

import java.util.ArrayList;

public class SyllabusActivity extends AppCompatActivity implements SyllabusActivityListener {

    private TabLayout tabLayout;
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

        tabLayout = findViewById(R.id.tabLayout);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch(tab.getPosition()){
                    case 0: {
                        viewModel.getDataList();
                        viewModel.adapter.notifyDataSetChanged();
                        break;
                    } case 1 : {
                        viewModel.getBusinessAdministrationList();
                        viewModel.adapter.notifyDataSetChanged();
                        break;
                    } case 2 : {
                        viewModel.getMarketingList();
                        viewModel.adapter.notifyDataSetChanged();
                        break;
                    }
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewModel.getDataList();
    }

    @Override
    public void showLessons(Semester data) {
        String jsonArray = new Gson().toJson(data.getLessons());
        Log.d("JSON_ARRAY",jsonArray);
        startActivity(new Intent(this, SyllabusLessonsActivity.class).putExtra("ARRAY",jsonArray));
    }
}
