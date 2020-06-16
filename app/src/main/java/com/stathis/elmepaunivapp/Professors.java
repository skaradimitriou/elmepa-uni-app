package com.stathis.elmepaunivapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.BaseAdapter;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.stathis.elmepaunivapp.models.ProfessorModel;
import com.stathis.elmepaunivapp.recyclerview.RecAdapter;

import java.util.ArrayList;
import java.util.List;

public class Professors extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professors);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        List<ProfessorModel> professors = new ArrayList<>();
        professors.add(new ProfessorModel("Christos Lemonakis","test@gmail.com"));
        professors.add(new ProfessorModel("Christos Lemonakis1","test1@gmail.com"));
        professors.add(new ProfessorModel("Christos Lemonakis2","test2@gmail.com"));
        professors.add(new ProfessorModel("Christos Lemonakis3","test3@gmail.com"));

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setAdapter(new RecAdapter(professors));

        //bottom nav
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.nav_home);

        //listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent i;
                switch(item.getItemId()){
                    case R.id.nav_home:
                        i = new Intent(Professors.this,Dashboard.class);
                        startActivity(i);
                        return true;
                    case R.id.nav_one:
                    case R.id.nav_two:
                    case R.id.nav_three:
                }
                return false;
            }
        });
    }
}
