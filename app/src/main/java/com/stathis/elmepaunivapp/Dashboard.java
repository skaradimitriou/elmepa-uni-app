package com.stathis.elmepaunivapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.card.MaterialCardView;

public class Dashboard extends AppCompatActivity implements View.OnClickListener {

    private CardView updates,department,students,professors,placeholder,contact;

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
                i = new Intent(Dashboard.this,Announcements.class);
                startActivity(i);
                break;
            case R.id.to_tmima:
                i=new Intent(Dashboard.this, Department.class);
                startActivity(i);
                break;
            case R.id.students:
                i=new Intent(Dashboard.this, Students.class);
                startActivity(i);
                break;
            case R.id.professors:
                i=new Intent(Dashboard.this, Professors.class);
                startActivity(i);
                break;
            case R.id.placeholder:
                i=new Intent(Dashboard.this, PlaceholderClass.class);
                startActivity(i);
                break;
            case R.id.contact:
                i=new Intent(Dashboard.this, Contact.class);
                startActivity(i);
                break;
        }
    }
}
