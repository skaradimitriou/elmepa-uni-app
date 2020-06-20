package com.stathis.elmepaunivapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class PhdProg extends AppCompatActivity {

    private String url = "https://mst.hmu.gr/metaptyxiako/didaktorikes-spoydes/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phd_prog);
        //load webview with school web content
        WebView webview = findViewById(R.id.phd_Program);
        webview.loadUrl(url);
    }
}
