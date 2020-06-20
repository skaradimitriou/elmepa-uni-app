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
import com.stathis.elmepaunivapp.models.ProfessorModel;

public class Announcements extends AppCompatActivity {

    private WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcements);
        //load webview with school web content
        WebView webview = findViewById(R.id.web_announcements);
        webview.loadUrl("https://mst.hmu.gr/news_gr/");
        //enabling js files
        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.nav_home);
        //listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent i;
                switch(item.getItemId()){
                    case R.id.nav_home:
                        i = new Intent(Announcements.this, Dashboard.class);
                        startActivity(i);
                        return true;
                    case R.id.nav_students:
                        //
                    case R.id.nav_uni:
                        i = new Intent(Announcements.this, Department.class);
                        startActivity(i);
                    case R.id.nav_search:
                        i = new Intent(Announcements.this, Professors.class);
                        startActivity(i);
                }
                return false;
            }
        });

    }
}
