package com.stathis.elmepaunivapp.ui.announcements;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.stathis.elmepaunivapp.AnnouncementMoreInfo;
import com.stathis.elmepaunivapp.ui.dashboard.Dashboard;
import com.stathis.elmepaunivapp.ui.department.Department;
import com.stathis.elmepaunivapp.ui.professors.Professors;
import com.stathis.elmepaunivapp.R;
import com.stathis.elmepaunivapp.ui.announcements.model.Announcement;
import com.stathis.elmepaunivapp.recyclerview.LatestNewsAdapter;
import com.stathis.elmepaunivapp.listeners.NewsClickListener;
import com.stathis.elmepaunivapp.ui.students.Students;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class Announcements extends AppCompatActivity {

    private RecyclerView ann_recView;
    private LatestNewsAdapter ann_adapter;
    private ArrayList<Announcement> announcements = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcements);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.nav_home);

        Content content = new Content();
        content.execute();

        //Announcements recView & Adapters
        ann_recView = findViewById(R.id.latestNews_recView);
        ann_adapter = new LatestNewsAdapter(announcements, new NewsClickListener() {
            @Override
            public void onNewsClick(Announcement announcement) {
                Intent learnMore = new Intent(Announcements.this, AnnouncementMoreInfo.class);
                learnMore.putExtra("AnnouncementUrl", announcement.getOpenUrl());
                startActivity(learnMore);
                overridePendingTransition(0, 0);
            }

            @Override
            public void onClick(View v) {
                //do nothing
            }
        });
        ann_recView.setAdapter(ann_adapter);

        //listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent i;
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        i = new Intent(Announcements.this, Dashboard.class);
                        startActivity(i);
                        overridePendingTransition(0, 0);
                    case R.id.nav_students:
                        i = new Intent(Announcements.this, Students.class);
                        startActivity(i);
                        overridePendingTransition(0, 0);
                        break;
                    case R.id.nav_uni:
                        i = new Intent(Announcements.this, Department.class);
                        startActivity(i);
                        overridePendingTransition(0, 0);
                        break;
                    case R.id.nav_search:
                        i = new Intent(Announcements.this, Professors.class);
                        startActivity(i);
                        overridePendingTransition(0, 0);
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

    private class Content extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
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
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

}



