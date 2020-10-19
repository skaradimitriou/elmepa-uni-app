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
import com.stathis.elmepaunivapp.database.AnnouncementsDao;
import com.stathis.elmepaunivapp.database.AnnouncementsDatabase;
import com.stathis.elmepaunivapp.ui.dashboard.Dashboard;
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

public class Announcements extends AppCompatActivity implements NewsClickListener {

    private AnnouncementsDatabase announcementsDatabase;
    private SwipeRefreshLayout swipe_refresh_layout;
    private RecyclerView ann_recView;
    private LatestNewsAdapter ann_adapter;
    private ArrayList<Announcement> announcements = new ArrayList<>();
    private AnnouncementsViewModel announcementsViewModel;
    private Content content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcements);
        //initializing viewmodel for this activity
        announcementsViewModel = new ViewModelProvider(this).get(AnnouncementsViewModel.class);
        announcementsDatabase = AnnouncementsDatabase.getInstance(this);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.nav_home);


        // TODO(" Implement empty view if someone has no internet connection")
        content = new Content();
        content.execute();

        // <!---- Announcements recycler view & adapter start ---->
        ann_recView = findViewById(R.id.latestNews_recView);
        ann_adapter = new LatestNewsAdapter(this);
        ann_adapter.submitList(announcementsDatabase.getAnnouncementDao().getAll());
        ann_recView.setAdapter(ann_adapter);

        // <!---- Announcements recycler view & adapter end ---->

        swipe_refresh_layout = findViewById(R.id.swipe_refresh_layout);
        swipe_refresh_layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                content = new Content();
                content.execute();
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
    public void onNewsClick(Announcement announcement) {
        Intent learnMore = new Intent(Announcements.this, WebviewActivity.class);
        learnMore.putExtra("URL", announcement.getOpenUrl());
        startActivity(learnMore);
        overridePendingTransition(0, 0);
    }

    public boolean dbIsEmpty() {
        List<Announcement> announcements = announcementsDatabase.getAnnouncementDao().getAll();
        if (announcements.size() < 1) {
            return true;
        }
        return false;
    }

    private class Content extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            ann_adapter.submitList(announcementsDatabase.getAnnouncementDao().getAll());
            ann_adapter.notifyDataSetChanged();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                String url = "https://mst.hmu.gr/news_gr/";
                Document doc = Jsoup.connect(url).get();
                Elements data = doc.select("article");
                int size = data.size();
                Log.d("doc", "doc: " + doc);
                Log.d("data", "data: " + data);
                for (int i = 0; i < size - 1; i++) {
                    String imgUrl = data.select("a.entry-featured-image-url")
                            .select("img")
                            .eq(i)
                            .attr("src");

                    String title = data.select("h2.entry-title")
                            .select("h2")
                            .eq(i)
                            .text();

                    String detailUrl = data.select("h2.entry-title")
                            .select("a")
                            .eq(i)
                            .attr("href");
                    announcements.add(new Announcement(title, detailUrl, imgUrl));
                    Log.d("items", "img: " + imgUrl + " . title: " + title);
                }
                announcements.add(new Announcement("Δείτε όλες τις ανακοινώσεις του Τμήματος", "https://mst.hmu.gr/news_gr/", "https://mst.hmu.gr/wp-content/uploads/2020/06/student-using-laptop-library_74855-2539-400x250.jpg"));

                /* checks if announcements db size is less than 0 (empty) and either inserts or updates the data
                then passes the data into the ListAdapter   */

                if (dbIsEmpty()) {
                    announcementsDatabase.getAnnouncementDao().insert(announcements);
                } else {
                    announcementsDatabase.getAnnouncementDao().update(announcements);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}