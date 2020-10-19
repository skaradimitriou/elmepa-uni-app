package com.stathis.elmepaunivapp.ui.webview;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.stathis.elmepaunivapp.R;
import com.stathis.elmepaunivapp.ui.dashboard.Dashboard;
import com.stathis.elmepaunivapp.ui.department.Department;
import com.stathis.elmepaunivapp.ui.professors.Professors;
import com.stathis.elmepaunivapp.ui.students.Students;

public class WebviewActivity extends AppCompatActivity {

    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        //passing the url data from previous activities as "URL"
        url = getIntent().getStringExtra("URL");

        //load webview with school web content
        WebView webview = findViewById(R.id.webview);
        webview.loadUrl(url);

        //enabling web js files
        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);

        //TODO("Implement screen if no internet connection")

        //bottom navigation bar
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.nav_home);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        startActivity(new Intent(WebviewActivity.this, Dashboard.class));
                        break;
                    case R.id.nav_students:
                        startActivity(new Intent(WebviewActivity.this, Students.class));
                        break;
                    case R.id.nav_uni:
                        startActivity(new Intent(WebviewActivity.this, Department.class));
                        break;
                    case R.id.nav_search:
                        startActivity(new Intent(WebviewActivity.this, Professors.class));
                        break;
                }
                return false;
            }
        });
    }
}