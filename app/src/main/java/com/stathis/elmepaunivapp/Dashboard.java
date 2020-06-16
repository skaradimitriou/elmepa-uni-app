package com.stathis.elmepaunivapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.card.MaterialCardView;

public class Dashboard extends AppCompatActivity implements View.OnClickListener {

    private MaterialCardView updates,department,students,professors,placeholder,contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        updates = findViewById(R.id.anakoinoseis);
        department = findViewById(R.id.to_tmima);
        students = findViewById(R.id.students);
        professors = findViewById(R.id.professors);
        placeholder = findViewById(R.id.placeholder);
        contact = findViewById(R.id.contact);

        //listeners
        updates.setOnClickListener(this);
        department.setOnClickListener(this);
        students.setOnClickListener(this);
        professors.setOnClickListener(this);
        placeholder.setOnClickListener(this);
        contact.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i;
        //cases bellow
        switch(v.getId()){
            case R.id.anakoinoseis:

            case R.id.to_tmima:

            case R.id.students:

            case R.id.professors:

            case R.id.placeholder:

            case R.id.contact:

        }
    }
}
