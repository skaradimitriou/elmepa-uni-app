package com.stathis.elmepaunivapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class PostgraduateProg extends AppCompatActivity {

    private WebView webview;
    private String url = "https://mst.hmu.gr/metaptyxiako/metaptychiako-programma/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postgraduate_prog);
        //load webview with school web content
        WebView webview = findViewById(R.id.postGradProgram);
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
