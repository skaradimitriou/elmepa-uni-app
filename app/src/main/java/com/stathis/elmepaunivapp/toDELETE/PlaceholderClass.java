package com.stathis.elmepaunivapp.toDELETE;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.stathis.elmepaunivapp.R;

public class PlaceholderClass extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placeholder_class);
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }
}
