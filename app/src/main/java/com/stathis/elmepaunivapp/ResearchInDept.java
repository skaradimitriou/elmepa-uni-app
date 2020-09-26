package com.stathis.elmepaunivapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.stathis.elmepaunivapp.models.DeptFieldsOfStudy;
import com.stathis.elmepaunivapp.ui.professors.model.ProfessorModel;
import com.stathis.elmepaunivapp.models.Programmes;
import com.stathis.elmepaunivapp.models.SocialChannels;
import com.stathis.elmepaunivapp.ui.students.model.UsefulLinks;
import com.stathis.elmepaunivapp.listeners.ItemClickListener;
import com.stathis.elmepaunivapp.recyclerviews.UsefulLinksAdapter;
import com.stathis.elmepaunivapp.ui.dashboard.Dashboard;
import com.stathis.elmepaunivapp.ui.professors.Professors;
import com.stathis.elmepaunivapp.ui.students.Students;

import java.util.ArrayList;

public class ResearchInDept extends AppCompatActivity {

    private RecyclerView researchItems, research_labs;
    private UsefulLinksAdapter researchAdapter, researchLabsAdapter;
    private ArrayList<UsefulLinks> researchItemList = new ArrayList<>();
    private ArrayList<UsefulLinks> researchLabList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_research_in_dept);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        createLists();

        researchItems = findViewById(R.id.research_InDept_recView);
        researchAdapter = new UsefulLinksAdapter(researchItemList, new ItemClickListener() {
            @Override
            public void onItemClick(DeptFieldsOfStudy item) {

            }

            @Override
            public void onProgrammesClick(Programmes programmes) {

            }

            @Override
            public void onProfessorClick(ProfessorModel professorModel) {

            }

            @Override
            public void onUsefulLinksClick(UsefulLinks usefulLinks) {
                switch (usefulLinks.getName()) {
                    case "Ινστιτούτο Οικονομικής Ανάλυσης":
                        Intent learnMore = new Intent(ResearchInDept.this, ResearchLearnMore.class);
                        learnMore.putExtra("LEARN_MORE_URL", usefulLinks.getUrl());
                        startActivity(learnMore);
                        overridePendingTransition(0, 0);
                        break;
                    case "Ερευνητικά Επιτεύγματα":
                        Intent achievements = new Intent(ResearchInDept.this, ResearchLearnMore.class);
                        achievements.putExtra("LEARN_MORE_URL", usefulLinks.getUrl());
                        startActivity(achievements);
                        overridePendingTransition(0, 0);
                        break;
                    case "Δημοσιεύσεις":
                        Intent papers = new Intent(ResearchInDept.this, ResearchLearnMore.class);
                        papers.putExtra("LEARN_MORE_URL", usefulLinks.getUrl());
                        startActivity(papers);
                        overridePendingTransition(0, 0);
                        break;
                    case "Στατιστικά Στοιχεία":
                        Intent statistics = new Intent(ResearchInDept.this, ResearchLearnMore.class);
                        statistics.putExtra("LEARN_MORE_URL", usefulLinks.getUrl());
                        startActivity(statistics);
                        overridePendingTransition(0, 0);
                        break;
                }
            }

            @Override
            public void onSocialItemClick(SocialChannels socialChannels) {

            }

            @Override
            public void onClick(View v) {

            }
        });
        researchItems.setAdapter(researchAdapter);
        research_labs = findViewById(R.id.research_Labs_recView);
        researchLabsAdapter = new UsefulLinksAdapter(researchLabList, new ItemClickListener() {
            @Override
            public void onItemClick(DeptFieldsOfStudy item) {

            }

            @Override
            public void onProgrammesClick(Programmes programmes) {

            }

            @Override
            public void onProfessorClick(ProfessorModel professorModel) {

            }

            @Override
            public void onUsefulLinksClick(UsefulLinks usefulLinks) {
                switch (usefulLinks.getName()) {
                    case "Εργαστήριο Διοικητικής Οικονομικής και Συστημάτων Αποφάσεων":
                        Intent firstLab = new Intent(ResearchInDept.this, ResearchLearnMore.class);
                        firstLab.putExtra("LEARN_MORE_URL", usefulLinks.getUrl());
                        startActivity(firstLab);
                        overridePendingTransition(0, 0);
                        break;
                    case "Εργαστήριο Επιστήμης Δεδομένων, Πολυμέσων και Μοντελοποίησης":
                        Intent SecondLab = new Intent(ResearchInDept.this, ResearchLearnMore.class);
                        SecondLab.putExtra("LEARN_MORE_URL", usefulLinks.getUrl());
                        startActivity(SecondLab);
                        overridePendingTransition(0, 0);
                        break;
                    case "Εργαστήριο Ηλεκτρονικής Επιχειρηματικής Ευφυΐας":
                        Intent ThirdLab = new Intent(ResearchInDept.this, ResearchLearnMore.class);
                        ThirdLab.putExtra("LEARN_MORE_URL", usefulLinks.getUrl());
                        startActivity(ThirdLab);
                        overridePendingTransition(0, 0);
                        break;
                }
            }

            @Override
            public void onSocialItemClick(SocialChannels socialChannels) {

            }

            @Override
            public void onClick(View v) {

            }
        });
        research_labs.setAdapter(researchLabsAdapter);

        //bottom navigation
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.nav_uni);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent i;
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        i = new Intent(ResearchInDept.this, Dashboard.class);
                        startActivity(i);
                        overridePendingTransition(0, 0);
                        break;
                    case R.id.nav_students:
                        i = new Intent(ResearchInDept.this, Students.class);
                        startActivity(i);
                        overridePendingTransition(0, 0);
                        break;
                    case R.id.nav_uni:
                        return true;
                    case R.id.nav_search:
                        i = new Intent(ResearchInDept.this, Professors.class);
                        startActivity(i);
                        overridePendingTransition(0, 0);
                        break;
                }
                return false;
            }
        });
    }

    private void createLists() {
        researchItemList.add(new UsefulLinks("Ινστιτούτο Οικονομικής Ανάλυσης", "https://mst.hmu.gr/ereuna/institoyto-oikonomikhs-analyshs-epicheirhmatikothtas-kai-toyrismoy/", R.drawable.institute));
        researchItemList.add(new UsefulLinks("Ερευνητικά Επιτεύγματα", "https://mst.hmu.gr/ereuna/ereynhtika-epiteygmata/", R.drawable.achievements));
        researchItemList.add(new UsefulLinks("Δημοσιεύσεις", "https://mst.hmu.gr/ereuna/dhmosieyseis/", R.drawable.papers));
        researchItemList.add(new UsefulLinks("Στατιστικά Στοιχεία", "https://mst.hmu.gr/ereuna/statistika-stoicheia/", R.drawable.analytics));

        researchLabList.add(new UsefulLinks("Εργαστήριο Διοικητικής Οικονομικής και Συστημάτων Αποφάσεων", "https://mst.hmu.gr/ereuna/adeds/", R.drawable.lab));
        researchLabList.add(new UsefulLinks("Εργαστήριο Επιστήμης Δεδομένων, Πολυμέσων και Μοντελοποίησης", "https://mst.hmu.gr/ereuna/datalab/", R.drawable.lab));
        researchLabList.add(new UsefulLinks("Εργαστήριο Ηλεκτρονικής Επιχειρηματικής Ευφυΐας", "https://www.e-bilab.gr/", R.drawable.lab));
    }
}
