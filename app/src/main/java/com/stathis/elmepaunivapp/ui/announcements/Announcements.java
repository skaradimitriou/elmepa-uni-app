package com.stathis.elmepaunivapp.ui.announcements;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.stathis.elmepaunivapp.database.AnnouncementsDatabase;
import com.stathis.elmepaunivapp.listeners.AnnouncementClickListener;
import com.stathis.elmepaunivapp.ui.department.Department;
import com.stathis.elmepaunivapp.ui.professors.Professors;
import com.stathis.elmepaunivapp.R;
import com.stathis.elmepaunivapp.ui.announcements.model.Announcement;
import com.stathis.elmepaunivapp.ui.announcements.recyclerviews.LatestNewsAdapter;
import com.stathis.elmepaunivapp.listeners.NewsClickListener;
import com.stathis.elmepaunivapp.ui.students.Students;
import com.stathis.elmepaunivapp.ui.webview.WebviewActivity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Announcements extends AppCompatActivity implements AnnouncementClickListener {

    private AnnouncementsDatabase announcementsDatabase;
    private SwipeRefreshLayout swipe_refresh_layout;
    private RecyclerView announcementsRecycler;
    private ArrayList<Announcement> announcements = new ArrayList<>();
    private AnnouncementsViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcements);
        //initializing viewmodel for this activity
        viewModel = new ViewModelProvider(this).get(AnnouncementsViewModel.class);
        viewModel.setupDatabase(this);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.nav_home);


        // TODO(" Implement empty view if someone has no internet connection")
        viewModel.getAnnouncements();

        // <!---- Announcements recycler view & adapter start ---->
        announcementsRecycler = findViewById(R.id.latestNews_recView);
        viewModel.setUpListener(this);
        announcementsRecycler.setAdapter(viewModel.adapter);

        // <!---- Announcements recycler view & adapter end ---->
        swipe_refresh_layout = findViewById(R.id.swipe_refresh_layout);
        swipe_refresh_layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                viewModel.getAnnouncements();
                swipe_refresh_layout.setRefreshing(false);
            }
        });

        // <!---- Bottom Navigation Listener start ---->
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        return true;
                    case R.id.nav_students:
                        startActivity(new Intent(Announcements.this, Students.class));
                        break;
                    case R.id.nav_uni:
                        startActivity(new Intent(Announcements.this, Department.class));
                        break;
                    case R.id.nav_search:
                        startActivity(new Intent(Announcements.this, Professors.class));
                        break;
                }
                return false;
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }

    @Override
    public void goToAnnouncement(Announcement announcement) {
        startActivity(new Intent(Announcements.this, WebviewActivity.class).putExtra("URL", announcement.getOpenUrl()));
    }

}