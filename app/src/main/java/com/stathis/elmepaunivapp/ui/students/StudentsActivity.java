package com.stathis.elmepaunivapp.ui.students;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.stathis.elmepaunivapp.listeners.activity_listeners.StudentsActivityListener;
import com.stathis.elmepaunivapp.ui.dashboard.DashboardActivity;
import com.stathis.elmepaunivapp.ui.department.DepartmentActivity;
import com.stathis.elmepaunivapp.ui.professors.ProfessorsActivity;
import com.stathis.elmepaunivapp.R;
import com.stathis.elmepaunivapp.ui.students.model.Schedule;
import com.stathis.elmepaunivapp.ui.students.model.UsefulLinks;
import com.stathis.elmepaunivapp.ui.syllabus.SyllabusActivity;
import com.stathis.elmepaunivapp.ui.webview.WebviewActivity;

public class StudentsActivity extends AppCompatActivity implements StudentsActivityListener {

    private RecyclerView recyclerView;
    private StudentsViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students);
        viewModel = new ViewModelProvider(this).get(StudentsViewModel.class);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        recyclerView = findViewById(R.id.students_activity_recycler);
        recyclerView.setAdapter(viewModel.adapter);
        viewModel.createList(this);

        //bottom navigation & listener
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.nav_students);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        startActivity(new Intent(StudentsActivity.this, DashboardActivity.class));
                        break;
                    case R.id.nav_students:
                        return true;
                    case R.id.nav_uni:
                        startActivity(new Intent(StudentsActivity.this, DepartmentActivity.class));
                        break;
                    case R.id.nav_search:
                        startActivity(new Intent(StudentsActivity.this, ProfessorsActivity.class));
                        break;
                }
                return false;
            }
        });
    }

    @Override
    public void openStudentsSchedule(Schedule schedule) {
        startActivity(new Intent(this, WebviewActivity.class).putExtra("URL", "https://mst.hmu.gr/proptyxiako/orologio-programma-mathimaton/"));
    }

    @Override
    public void itemClicked(UsefulLinks usefulLinks) {
        startActivity(new Intent(this, WebviewActivity.class).putExtra("URL", usefulLinks.getUrl()));
    }

    @Override
    public void goToSyllabus(UsefulLinks usefulLinks) {
        startActivity(new Intent(this, SyllabusActivity.class));
    }
}