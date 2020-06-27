package com.stathis.elmepaunivapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.stathis.elmepaunivapp.models.Announcement;
import com.stathis.elmepaunivapp.models.ProfessorModel;
import com.stathis.elmepaunivapp.recyclerview.LatestNewsAdapter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class Announcements extends AppCompatActivity {

    private WebView webview;
    private RecyclerView ann_recView;
    private LatestNewsAdapter ann_adapter;
    private ArrayList<Announcement> latestNews = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcements);
//        //load webview with school web content
//        WebView webview = findViewById(R.id.web_announcements);
//        webview.loadUrl("https://mst.hmu.gr/news_gr/");
//        //enabling js files
//        WebSettings webSettings = webview.getSettings();
//        webSettings.setJavaScriptEnabled(true);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.nav_home);

        Content content = new Content();
        content.execute();

        //Announcements recView
        ann_recView = findViewById(R.id.latestNews_recView);
        ann_adapter = new LatestNewsAdapter(latestNews);
        ann_recView.setAdapter(ann_adapter);

        //listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent i;
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        return true;
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

    private class Content extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            ann_adapter.notifyDataSetChanged();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                String url = "https://mst.hmu.gr/";
                Document doc = Jsoup.connect(url).get();

                Element data = doc.select("article").first();

                Log.d("doc", "doc: " + doc);
                Log.d("data", "data: " + data);
                int size = data.childNodeSize();
                Log.d("size", "size: " + size);
                for (int i = 0; i < size; i++) {
                    String imgUrl = data.select("a.entry-featured-image-url")
                            .select("img")
                            .eq(i)
                            .attr("src");

                    String title = data.select("h4.entry-title")
                            .select("h4")
                            .eq(i)
                            .text();

                    String detailUrl = data.select("h4.entry-title")
                            .select("a")
                            .eq(i)
                            .attr("href");
                    latestNews.add(new Announcement(title, detailUrl, imgUrl));
                    Log.d("items", "img: " + imgUrl + " . title: " + title);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

}



