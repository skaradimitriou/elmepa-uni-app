package com.stathis.elmepaunivapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.stathis.elmepaunivapp.models.DeptFieldsOfStudy;
import com.stathis.elmepaunivapp.models.Programmes;
import com.stathis.elmepaunivapp.models.UsefulLinks;
import com.stathis.elmepaunivapp.recyclerview.FieldsAdapter;
import com.stathis.elmepaunivapp.recyclerview.ItemClickListener;
import com.stathis.elmepaunivapp.recyclerview.UsefulLinksAdapter;

import java.util.ArrayList;

public class Students extends AppCompatActivity {

    private RecyclerView fields_recView, useful_links_recView;
    private FieldsAdapter fieldsAdapter;
    private UsefulLinksAdapter usefulLinksAdapter;
    private ArrayList<DeptFieldsOfStudy> fieldsOfStudy;
    private ArrayList<UsefulLinks> usefulLinks;
    private Button btn;
    private CardView schedule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        //createLists
        createLists();

        //rec Views & adapters
        fields_recView = findViewById(R.id.diff_directions);
        useful_links_recView = findViewById(R.id.useful_links_recView);
        fieldsAdapter = new FieldsAdapter(fieldsOfStudy, new ItemClickListener() {
            @Override
            public void onClick(View v) {
            }

            //do nothing
            @Override
            public void onItemClick(DeptFieldsOfStudy item) {
                //do nothing
            }

            @Override
            public void onProgrammesClick(Programmes programmes) {
                //do nothing
            }

            @Override
            public void onUsefulLinksClick(UsefulLinks usefulLinks) {
                //do nothing
            }
        });

        usefulLinksAdapter = new UsefulLinksAdapter(usefulLinks, new ItemClickListener() {
            @Override
            public void onItemClick(DeptFieldsOfStudy item) {
                //do nothing
            }

            @Override
            public void onProgrammesClick(Programmes programmes) {
                //do nothing
            }

            @Override
            public void onUsefulLinksClick(UsefulLinks usefulLinks) {
                Toast.makeText(Students.this, "You did it!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onClick(View v) {
                //do nothing
            }
        });
        useful_links_recView.setAdapter(usefulLinksAdapter);
        fields_recView.setAdapter(fieldsAdapter);

        schedule = findViewById(R.id.schedule_cardview);
        schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //click action
            }
        });

        btn = findViewById(R.id.schedule_card_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //click action
            }
        });

        //bottom navigation & listener
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.nav_students);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent i;
                switch(item.getItemId()){
                    case R.id.nav_home:
                        i = new Intent(Students.this, Dashboard.class);
                        startActivity(i);
                        break;
                    case R.id.nav_students:
                        return true;
                    case R.id.nav_uni:
                        i = new Intent(Students.this, Department.class);
                        startActivity(i);
                        break;
                    case R.id.nav_search:
                        i = new Intent(Students.this, Professors.class);
                        startActivity(i);
                        break;
                }
                return false;
            }
        });
    }

    private void createLists() {
        fieldsOfStudy = new ArrayList<>();
        fieldsOfStudy.add(new DeptFieldsOfStudy("Επιστήμη των Δεδομένων & Τεχνολογίες Πληροφορικής", R.drawable.data));
        fieldsOfStudy.add(new DeptFieldsOfStudy("Διοίκηση Επιχειρήσεων & Οργανισμών", R.drawable.business));
        fieldsOfStudy.add(new DeptFieldsOfStudy("Ψηφιακό Μάρκετινγκ και Επικοινωνία", R.drawable.digitalmkt));

        usefulLinks = new ArrayList<>();
        usefulLinks.add(new UsefulLinks("Εικονική Περιήγηση","",R.drawable.data));
        usefulLinks.add(new UsefulLinks("Ακαδημαική Ταυτότητα","",R.drawable.data));
        usefulLinks.add(new UsefulLinks("Σύστημα Φοιτητών","",R.drawable.data));
        usefulLinks.add(new UsefulLinks("Σελίδα Φοιτητών","",R.drawable.data));
        usefulLinks.add(new UsefulLinks("Δήμος Αγ.Νικολάου","",R.drawable.data));
        usefulLinks.add(new UsefulLinks("Προτεινόμενα Εστιατόρια","",R.drawable.data));
        usefulLinks.add(new UsefulLinks("Εύδοξος","",R.drawable.data));
        usefulLinks.add(new UsefulLinks("Edu E-mail Φοιτητή","",R.drawable.data));
        usefulLinks.add(new UsefulLinks("Events Τμήματος","",R.drawable.data));
        usefulLinks.add(new UsefulLinks("Κ.Τ.Ε.Λ","",R.drawable.data));
        usefulLinks.add(new UsefulLinks("Movie SCoRe","",R.drawable.data));

    }
}
