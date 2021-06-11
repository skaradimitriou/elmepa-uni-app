package com.stathis.elmepaunivapp.ui.announcements;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.stathis.elmepaunivapp.abstraction.AbstractActivity;
import com.stathis.elmepaunivapp.listeners.activity_listeners.AnnouncementClickListener;
import com.stathis.elmepaunivapp.ui.department.DepartmentActivity;
import com.stathis.elmepaunivapp.R;
import com.stathis.elmepaunivapp.ui.announcements.model.Announcement;
import com.stathis.elmepaunivapp.ui.professors.ProfessorActivity;
import com.stathis.elmepaunivapp.ui.students.StudentsActivity;
import com.stathis.elmepaunivapp.ui.webview.WebviewActivity;

public class AnnouncementActivity extends AbstractActivity implements AnnouncementClickListener {

    private SwipeRefreshLayout swipe_refresh_layout;
    private BottomNavigationView bottomNavigationView;
    private RecyclerView announcementsRecycler;
    private AnnouncementsViewModel viewModel;

    public AnnouncementActivity() {
        super(R.layout.activity_announcements);
    }

    @Override
    public void initial() {
        viewModel = new ViewModelProvider(this).get(AnnouncementsViewModel.class);
        viewModel.setupDatabase(this);

        bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.nav_home);

        announcementsRecycler = findViewById(R.id.latestNews_recView);
        swipe_refresh_layout = findViewById(R.id.swipe_refresh_layout);
    }

    @Override
    public void running() {
        viewModel.getAnnouncements();

        viewModel.setUpListener(this);
        announcementsRecycler.setAdapter(viewModel.adapter);

        swipe_refresh_layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                viewModel.getAnnouncements();
                swipe_refresh_layout.setRefreshing(false);
            }
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        return true;
                    case R.id.nav_students:
                        startActivity(new Intent(AnnouncementActivity.this, StudentsActivity.class));
                        break;
                    case R.id.nav_uni:
                        startActivity(new Intent(AnnouncementActivity.this, DepartmentActivity.class));
                        break;
                    case R.id.nav_search:
                        startActivity(new Intent(AnnouncementActivity.this, ProfessorActivity.class));
                        break;
                }
                return false;
            }
        });
    }

    @Override
    public void stopped() {}

    @Override
    public void goToAnnouncement(Announcement announcement) {
        startActivity(new Intent(AnnouncementActivity.this, WebviewActivity.class).putExtra("URL", announcement.getOpenUrl()));
    }
}