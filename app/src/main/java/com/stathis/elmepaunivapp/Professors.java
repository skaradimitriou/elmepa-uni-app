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
        professors.add(new ProfessorModel("Στέλιος Παπαδάκης","spap@hmu.gr"));
        professors.add(new ProfessorModel("Κώστας Παναγιωτάκης","cpanag@hmu.gr"));
        professors.add(new ProfessorModel("Γιώργος Μαστοράκης","gmastorakis@hmu.gr"));
        professors.add(new ProfessorModel("Γιάννης Κοπανάκης","kopanakis@hmu.gr"));
        professors.add(new ProfessorModel("Γιάννης Δημοτίκαλης","jdim@hmu.gr"));
        professors.add(new ProfessorModel("Χρήστος Λεμονάκης","lemonakis@hmu.gr"));
        professors.add(new ProfessorModel("Μάνος Περακάκης","mperakakis@hmu.gr"));
        professors.add(new ProfessorModel("Αικατερινίδης Ιωάννης","aikaterinidis@gmail.com"));
        professors.add(new ProfessorModel("Αστρουλάκης Νικόλαος","n.astroulakis@gmail.com"));
        professors.add(new ProfessorModel("Βάρδας Ιωάννης","vardasg@hmu.gr"));
        professors.add(new ProfessorModel("Βασιλειάδης Γεώργιος","gvasil@hmu.gr, giorgos.vasiliadis@gmail.com"));
        professors.add(new ProfessorModel("Κοκκινάκης Εμμανουήλ","manoskokkinakis@yahoo.gr"));
        professors.add(new ProfessorModel("Κόττη Εύη","kottievi@hmu.gr"));
        professors.add(new ProfessorModel("Μαρκάκη Μαρία","mmarkaki@hmu.gr"));
        professors.add(new ProfessorModel("Μεραμβελιωτάκης Γεώργιος","gmeramv@hmu.gr"));
        professors.add(new ProfessorModel("Μπάλλας Παναγιώτης","ballas@hmu.gr"));
        professors.add(new ProfessorModel("Σκουλουδάκης Εμμανουήλ","Εskououdakis@hmu.gr, eskouloudakis@hotmail.com "));

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
