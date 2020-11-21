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
import com.stathis.elmepaunivapp.abstraction.AbstractActivity;
import com.stathis.elmepaunivapp.ui.dashboard.DashboardActivity;
import com.stathis.elmepaunivapp.ui.department.DepartmentActivity;
import com.stathis.elmepaunivapp.ui.professors.ProfessorsActivity;
import com.stathis.elmepaunivapp.ui.students.StudentsActivity;

public class WebviewActivity extends AbstractActivity {

    private String url;
    private WebView webview;
    private BottomNavigationView bottomNavigationView;

    public WebviewActivity() {
        super(R.layout.activity_webview);
    }

    @Override
    public void initial() {
        url = getIntent().getStringExtra("URL");

        webview = findViewById(R.id.webview);
        bottomNavigationView = findViewById(R.id.bottom_nav);

        checkInternetConnection();
    }

    @Override
    public void running() {
        webview.loadUrl(url);

        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);

        bottomNavigationView.setSelectedItemId(R.id.nav_home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        startActivity(new Intent(WebviewActivity.this, DashboardActivity.class));
                        break;
                    case R.id.nav_students:
                        startActivity(new Intent(WebviewActivity.this, StudentsActivity.class));
                        break;
                    case R.id.nav_uni:
                        startActivity(new Intent(WebviewActivity.this, DepartmentActivity.class));
                        break;
                    case R.id.nav_search:
                        startActivity(new Intent(WebviewActivity.this, ProfessorsActivity.class));
                        break;
                }
                return false;
            }
        });
    }

    @Override
    public void stopped() {

    }

    private void checkInternetConnection(){
        // Implement screen if no internet connection
    }
}