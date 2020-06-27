package com.stathis.elmepaunivapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class UndergraduateProg extends AppCompatActivity {

    private WebView webview;
    private String url = "https://mst.hmu.gr/tmima/ypopshphioi-phoithtes/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_undergraduate_prog);
        //load webview with school web content
        WebView webview = findViewById(R.id.undergraduateProgram);
        webview.loadUrl(url);
        //enabling js files
        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }
}
