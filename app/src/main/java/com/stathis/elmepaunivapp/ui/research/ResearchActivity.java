package com.stathis.elmepaunivapp.ui.research;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.stathis.elmepaunivapp.R;
import com.stathis.elmepaunivapp.listeners.activity_listeners.ResearchActivityClickListener;
import com.stathis.elmepaunivapp.ui.students.model.UsefulLinks;
import com.stathis.elmepaunivapp.ui.dashboard.Dashboard;
import com.stathis.elmepaunivapp.ui.professors.ProfessorsActivity;
import com.stathis.elmepaunivapp.ui.students.Students;
import com.stathis.elmepaunivapp.ui.webview.WebviewActivity;

public class ResearchActivity extends AppCompatActivity implements ResearchActivityClickListener {

    private RecyclerView researchItems, research_labs;
    private ResearchViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_research_in_dept);
        viewModel = new ViewModelProvider(this).get(ResearchViewModel.class);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        researchItems = findViewById(R.id.research_InDept_recView);
        research_labs = findViewById(R.id.research_Labs_recView);

        researchItems.setAdapter(viewModel.researchAdapter);
        research_labs.setAdapter(viewModel.researchLabsAdapter);

        viewModel.initListener(this);
        viewModel.createLists();
        viewModel.displayData();

        //bottom navigation
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.nav_uni);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        startActivity(new Intent(ResearchActivity.this, Dashboard.class));
                        break;
                    case R.id.nav_students:
                        startActivity(new Intent(ResearchActivity.this, Students.class));
                        break;
                    case R.id.nav_uni:
                        return true;
                    case R.id.nav_search:
                        startActivity(new Intent(ResearchActivity.this, ProfessorsActivity.class));
                        break;
                }
                return false;
            }
        });
    }

    @Override
    public void itemClicked(UsefulLinks usefulLinks) {
        startActivity(new Intent(ResearchActivity.this, WebviewActivity.class).putExtra("URL", usefulLinks.getUrl()));
    }
}