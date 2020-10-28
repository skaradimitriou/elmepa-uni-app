package com.stathis.elmepaunivapp.ui.students;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.stathis.elmepaunivapp.listeners.FieldsOfStudyListener;
import com.stathis.elmepaunivapp.ui.dashboard.DashboardActivity;
import com.stathis.elmepaunivapp.ui.department.DepartmentActivity;
import com.stathis.elmepaunivapp.ui.professors.ProfessorsActivity;
import com.stathis.elmepaunivapp.R;
import com.stathis.elmepaunivapp.models.DeptFieldsOfStudy;
import com.stathis.elmepaunivapp.ui.webview.WebviewActivity;

public class StudentsActivity extends AppCompatActivity implements FieldsOfStudyListener,View.OnClickListener {

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

        viewModel.createList();

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

    private void openSchedule() {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://mst.hmu.gr/proptyxiako/%cf%89%cf%81%ce%bf%ce%bb%cf%8c%ce%b3%ce%b9%ce%bf-%cf%80%cf%81%cf%8c%ce%b3%cf%81%ce%b1%ce%bc%ce%bc%ce%b1-%ce%bc%ce%b1%ce%b8%ce%b7%ce%bc%ce%ac%cf%84%cf%89%ce%bd/")));
    }

    @Override
    public void onFieldOfStudyClick(DeptFieldsOfStudy fieldsOfStudy) {
        switch (fieldsOfStudy.getName()) {
            case "Ακαδημαϊκό Ημερολόγιο":
                startActivity(new Intent(StudentsActivity.this, WebviewActivity.class).putExtra("URL", "https://mst.hmu.gr/proptyxiako/akadhmaiko-hmerologio/"));
                break;
            case "Σύμβουλος Καθηγητής":
                startActivity(new Intent(StudentsActivity.this, WebviewActivity.class).putExtra("URL", "https://mst.hmu.gr/proptyxiako/symboylos-kathhghths/"));
                break;
            case "Πρόγραμμα Erasmus+":
                startActivity(new Intent(StudentsActivity.this, WebviewActivity.class).putExtra("URL", "https://mst.hmu.gr/proptyxiako/programma-erasmus-dia-bioy-mathhsh/"));
                break;
        }
    }

    @Override
    public void onClick(View v) {
        openSchedule();
    }
}