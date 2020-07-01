package com.stathis.elmepaunivapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfessorProfile extends AppCompatActivity {

    private CircleImageView prof_img;
    private TextView prof_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor_profile);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        Intent getProfData = getIntent();
        String name = getProfData.getStringExtra("PROFESSOR_NAME");


        prof_img = findViewById(R.id.prof_prof_img);
        prof_name = findViewById(R.id.professor_prof_name);

        prof_name.setText(name);
    }

}
