package com.stathis.elmepaunivapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AnnouncementMoreInfo extends AppCompatActivity {

    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement_more_info);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        url = getIntent().getStringExtra("AnnouncementUrl");
        //load webview with school web content
        WebView webview = findViewById(R.id.web_announcement);
        webview.loadUrl(url);
        //enabling js files
        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);

        //bottom navigation bar
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.nav_home);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent i;
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        i = new Intent(AnnouncementMoreInfo.this, Dashboard.class);
                        startActivity(i);
                        overridePendingTransition(0, 0);
                        break;
                    case R.id.nav_students:
                        i = new Intent(AnnouncementMoreInfo.this, Students.class);
                        startActivity(i);
                        overridePendingTransition(0, 0);
                        break;
                    case R.id.nav_uni:
                        i = new Intent(AnnouncementMoreInfo.this, Department.class);
                        startActivity(i);
                        overridePendingTransition(0, 0);
                        break;
                    case R.id.nav_search:
                        i = new Intent(AnnouncementMoreInfo.this, Professors.class);
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
}
