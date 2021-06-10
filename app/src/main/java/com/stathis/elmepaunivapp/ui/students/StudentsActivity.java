package com.stathis.elmepaunivapp.ui.students;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.stathis.elmepaunivapp.abstraction.AbstractActivity;
import com.stathis.elmepaunivapp.listeners.activity_listeners.StudentsActivityListener;
import com.stathis.elmepaunivapp.ui.dashboard.DashboardActivityKt;
import com.stathis.elmepaunivapp.ui.department.DepartmentActivity;
import com.stathis.elmepaunivapp.ui.professors.ProfessorsActivity;
import com.stathis.elmepaunivapp.R;
import com.stathis.elmepaunivapp.ui.students.model.Schedule;
import com.stathis.elmepaunivapp.ui.students.model.UsefulLinks;
import com.stathis.elmepaunivapp.ui.syllabus.SyllabusActivity;
import com.stathis.elmepaunivapp.ui.webview.WebviewActivity;

public class StudentsActivity extends AbstractActivity implements StudentsActivityListener {

    private RecyclerView recyclerView;
    private BottomNavigationView bottomNavigationView;
    private StudentsViewModel viewModel;

    public StudentsActivity() {
        super(R.layout.activity_students);
    }

    @Override
    public void initial() {
        viewModel = new ViewModelProvider(this).get(StudentsViewModel.class);

        recyclerView = findViewById(R.id.students_activity_recycler);
        bottomNavigationView = findViewById(R.id.bottom_nav);
    }

    @Override
    public void running() {
        recyclerView.setAdapter(viewModel.adapter);
        viewModel.createList(this);

        bottomNavigationView.setSelectedItemId(R.id.nav_students);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        startActivity(new Intent(StudentsActivity.this, DashboardActivityKt.class));
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
    public void stopped() {

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
        switch(usefulLinks.getName()){
            case "Επιστήμη των Δεδομένων & Τεχνολογίες Πληροφορικής" : {
                startActivity(new Intent(this, SyllabusActivity.class).putExtra("userTabChoice", 0));
                break;
            }
            case "Διοίκηση Επιχειρήσεων & Οργανισμών" : {
                startActivity(new Intent(this, SyllabusActivity.class).putExtra("userTabChoice",1));
                break;
            }
            case "Ψηφιακό Μάρκετινγκ και Επικοινωνία" : {
                startActivity(new Intent(this, SyllabusActivity.class).putExtra("userTabChoice",2));
                break;
            }
        }
    }
}