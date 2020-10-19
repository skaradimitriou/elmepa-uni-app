package com.stathis.elmepaunivapp.ui.research;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.stathis.elmepaunivapp.R;
import com.stathis.elmepaunivapp.ui.students.model.UsefulLinks;
import com.stathis.elmepaunivapp.listeners.UsefulLinkClickListener;
import com.stathis.elmepaunivapp.recyclerviews.UsefulLinksAdapter;
import com.stathis.elmepaunivapp.ui.dashboard.Dashboard;
import com.stathis.elmepaunivapp.ui.professors.Professors;
import com.stathis.elmepaunivapp.ui.students.Students;
import com.stathis.elmepaunivapp.ui.webview.WebviewActivity;

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
        researchAdapter = new UsefulLinksAdapter(new UsefulLinkClickListener() {
            @Override
            public void onUsefulLinksClick(UsefulLinks usefulLinks) {
                Intent learnMore = new Intent(ResearchInDept.this, WebviewActivity.class);
                learnMore.putExtra("URL", usefulLinks.getUrl());
                startActivity(learnMore);
                overridePendingTransition(0, 0);
            }
        });
        researchAdapter.submitList(researchItemList);
        researchItems.setAdapter(researchAdapter);

        research_labs = findViewById(R.id.research_Labs_recView);
        researchLabsAdapter = new UsefulLinksAdapter(new UsefulLinkClickListener() {
            @Override
            public void onUsefulLinksClick(UsefulLinks usefulLinks) {
                Intent firstLab = new Intent(ResearchInDept.this, WebviewActivity.class);
                firstLab.putExtra("URL", usefulLinks.getUrl());
                startActivity(firstLab);
                overridePendingTransition(0, 0);
            }
        });
        researchLabsAdapter.submitList(researchLabList);
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
