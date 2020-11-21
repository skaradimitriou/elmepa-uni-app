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
import com.stathis.elmepaunivapp.abstraction.AbstractActivity;
import com.stathis.elmepaunivapp.listeners.activity_listeners.ResearchActivityClickListener;
import com.stathis.elmepaunivapp.ui.students.model.UsefulLinks;
import com.stathis.elmepaunivapp.ui.dashboard.DashboardActivity;
import com.stathis.elmepaunivapp.ui.professors.ProfessorsActivity;
import com.stathis.elmepaunivapp.ui.students.StudentsActivity;
import com.stathis.elmepaunivapp.ui.webview.WebviewActivity;

public class ResearchActivity extends AbstractActivity implements ResearchActivityClickListener {

    private RecyclerView researchRecycler;
    private BottomNavigationView bottomNavigationView;
    private ResearchViewModel viewModel;

    public ResearchActivity() {
        super(R.layout.activity_research_in_dept);
    }

    @Override
    public void initial() {
        viewModel = new ViewModelProvider(this).get(ResearchViewModel.class);

        researchRecycler = findViewById(R.id.research_activity_recycler);
        researchRecycler.setAdapter(viewModel.researchFinalAdapter);
        bottomNavigationView= findViewById(R.id.bottom_nav);
    }

    @Override
    public void running() {
        viewModel.initListener(this);
        viewModel.createLists();
        viewModel.displayData();

        bottomNavigationView.setSelectedItemId(R.id.nav_uni);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        startActivity(new Intent(ResearchActivity.this, DashboardActivity.class));
                        break;
                    case R.id.nav_students:
                        startActivity(new Intent(ResearchActivity.this, StudentsActivity.class));
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
    public void stopped() {

    }

    @Override
    public void itemClicked(UsefulLinks usefulLinks) {
        startActivity(new Intent(ResearchActivity.this, WebviewActivity.class).putExtra("URL", usefulLinks.getUrl()));
    }
}