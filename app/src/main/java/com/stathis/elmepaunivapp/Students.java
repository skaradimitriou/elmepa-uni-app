package com.stathis.elmepaunivapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;
import com.stathis.elmepaunivapp.models.DeptFieldsOfStudy;
import com.stathis.elmepaunivapp.models.ProfessorModel;
import com.stathis.elmepaunivapp.models.Programmes;
import com.stathis.elmepaunivapp.models.SocialChannels;
import com.stathis.elmepaunivapp.models.UsefulLinks;
import com.stathis.elmepaunivapp.recyclerview.FieldsAdapter;
import com.stathis.elmepaunivapp.recyclerview.ItemClickListener;
import com.stathis.elmepaunivapp.recyclerview.UsefulLinksAdapter;

import java.util.ArrayList;

public class Students extends AppCompatActivity {

    private RecyclerView fields_recView, useful_links_recView, sMatters_recView;
    private FieldsAdapter fieldsAdapter, sMattersAdapter;
    private UsefulLinksAdapter usefulLinksAdapter;
    private ArrayList<DeptFieldsOfStudy> fieldsOfStudy, studentsMatters;
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
                switch (item.getName()) {
                    case "Επιστήμη των Δεδομένων & Τεχνολογίες Πληροφορικής":
                        Intent goToDataSyllabus = new Intent(Students.this, Syllabus.class);
                        goToDataSyllabus.putExtra("DIRECTION", item.getName());
                        startActivity(goToDataSyllabus);
                        break;
                    case "Διοίκηση Επιχειρήσεων & Οργανισμών":
                        Intent goToBASyllabus = new Intent(Students.this, Syllabus.class);
                        goToBASyllabus.putExtra("DIRECTION", item.getName());
                        startActivity(goToBASyllabus);
                        break;
                    case "Ψηφιακό Μάρκετινγκ και Επικοινωνία":
                        Intent goToMKTSyllabus = new Intent(Students.this, Syllabus.class);
                        goToMKTSyllabus.putExtra("DIRECTION", item.getName());
                        startActivity(goToMKTSyllabus);
                        break;
                }
            }

            @Override
            public void onProgrammesClick(Programmes programmes) {
            }

            @Override
            public void onProfessorClick(ProfessorModel professorModel) {
            }

            @Override
            public void onUsefulLinksClick(UsefulLinks usefulLinks) {
            }

            @Override
            public void onSocialItemClick(SocialChannels socialChannels) {
            }
        });
        usefulLinksAdapter = new UsefulLinksAdapter(usefulLinks, new ItemClickListener() {
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
                Intent card, edu, fb, eclass, municipality, tripadvisor, eudoxus, webmail, events, buses, app;
                switch (usefulLinks.getName()) {
                    case "Ακαδημαική Ταυτότητα":
                        card = new Intent(Intent.ACTION_VIEW, Uri.parse(usefulLinks.getUrl()));
                        startActivity(card);
                        break;
                    case "Σύστημα Φοιτητών":
                        edu = new Intent(Intent.ACTION_VIEW, Uri.parse(usefulLinks.getUrl()));
                        startActivity(edu);
                        break;
                    case "Σελίδα Φοιτητών":
                        fb = new Intent(Intent.ACTION_VIEW, Uri.parse(usefulLinks.getUrl()));
                        startActivity(fb);
                        break;
                    case "e-Class":
                        eclass = new Intent(Intent.ACTION_VIEW, Uri.parse(usefulLinks.getUrl()));
                        startActivity(eclass);
                        break;
                    case "Δήμος Αγ.Νικολάου":
                        municipality = new Intent(Intent.ACTION_VIEW, Uri.parse(usefulLinks.getUrl()));
                        startActivity(municipality);
                        break;
                    case "Προτεινόμενα Εστιατόρια":
                        tripadvisor = new Intent(Intent.ACTION_VIEW, Uri.parse(usefulLinks.getUrl()));
                        startActivity(tripadvisor);
                        break;
                    case "Εύδοξος":
                        eudoxus = new Intent(Intent.ACTION_VIEW, Uri.parse(usefulLinks.getUrl()));
                        startActivity(eudoxus);
                        break;
                    case "Edu E-mail Φοιτητή":
                        webmail = new Intent(Intent.ACTION_VIEW, Uri.parse(usefulLinks.getUrl()));
                        startActivity(webmail);
                        break;
                    case "Events Τμήματος":
                        events = new Intent(Intent.ACTION_VIEW, Uri.parse(usefulLinks.getUrl()));
                        startActivity(events);
                        break;
                    case "Κ.Τ.Ε.Λ Ηρακλείου - Λασιθίου":
                        buses = new Intent(Intent.ACTION_VIEW, Uri.parse(usefulLinks.getUrl()));
                        startActivity(buses);
                        break;
                    case "Εφαρμογή Movie SCoRe":
                        app = new Intent(Intent.ACTION_VIEW, Uri.parse(usefulLinks.getUrl()));
                        startActivity(app);
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
        useful_links_recView.setAdapter(usefulLinksAdapter);
        fields_recView.setAdapter(fieldsAdapter);

        sMatters_recView = findViewById(R.id.sMatters_recView);
        sMattersAdapter = new FieldsAdapter(studentsMatters, new ItemClickListener() {
            @Override
            public void onItemClick(DeptFieldsOfStudy item) {
                switch (item.getName()) {
                    case "Ακαδημαϊκό Ημερολόγιο":
                        String AcadUrl = "https://mst.hmu.gr/proptyxiako/akadhmaiko-hmerologio/";
                        Intent acadIntent = new Intent(Students.this, StudentMatters.class);
                        acadIntent.putExtra("StudentMattersUrl", AcadUrl);
                        startActivity(acadIntent);
                        break;
                    case "Σύμβουλος Καθηγητής":
                        String mentorUrl = "https://mst.hmu.gr/proptyxiako/symboylos-kathhghths/";
                        Intent mentors = new Intent(Students.this, StudentMatters.class);
                        mentors.putExtra("StudentMattersUrl", mentorUrl);
                        startActivity(mentors);
                        break;
                    case "Πρόγραμμα Erasmus+":
                        String ErasmusUrl = "https://mst.hmu.gr/proptyxiako/programma-erasmus-dia-bioy-mathhsh/";
                        Intent erasmus = new Intent(Students.this, StudentMatters.class);
                        erasmus.putExtra("StudentMattersUrl", ErasmusUrl);
                        startActivity(erasmus);
                        break;
                }
            }

            @Override
            public void onProgrammesClick(Programmes programmes) {

            }

            @Override
            public void onProfessorClick(ProfessorModel professorModel) {

            }

            @Override
            public void onUsefulLinksClick(UsefulLinks usefulLinks) {

            }

            @Override
            public void onSocialItemClick(SocialChannels socialChannels) {

            }

            @Override
            public void onClick(View v) {

            }
        });
        sMatters_recView.setAdapter(sMattersAdapter);

        schedule = findViewById(R.id.schedule_cardview);
        schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSchedule();
            }
        });

        btn = findViewById(R.id.schedule_card_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSchedule();
            }
        });

        //bottom navigation & listener
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.nav_students);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent i;
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        i = new Intent(Students.this, Dashboard.class);
                        startActivity(i);
                        overridePendingTransition(0, 0);
                        break;
                    case R.id.nav_students:
                        return true;
                    case R.id.nav_uni:
                        i = new Intent(Students.this, Department.class);
                        startActivity(i);
                        overridePendingTransition(0, 0);
                        break;
                    case R.id.nav_search:
                        i = new Intent(Students.this, Professors.class);
                        startActivity(i);
                        overridePendingTransition(0, 0);
                        break;
                }
                return false;
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }

    private void createLists() {
        fieldsOfStudy = new ArrayList<>();
        fieldsOfStudy.add(new DeptFieldsOfStudy("Επιστήμη των Δεδομένων & Τεχνολογίες Πληροφορικής", R.drawable.data));
        fieldsOfStudy.add(new DeptFieldsOfStudy("Διοίκηση Επιχειρήσεων & Οργανισμών", R.drawable.business));
        fieldsOfStudy.add(new DeptFieldsOfStudy("Ψηφιακό Μάρκετινγκ και Επικοινωνία", R.drawable.digitalmkt));

        studentsMatters = new ArrayList<>();
        studentsMatters.add(new DeptFieldsOfStudy("Ακαδημαϊκό Ημερολόγιο", R.drawable.acadschedule));
        studentsMatters.add(new DeptFieldsOfStudy("Σύμβουλος Καθηγητής", R.drawable.mentor));
        studentsMatters.add(new DeptFieldsOfStudy("Πρόγραμμα Erasmus+", R.drawable.erasmus));

        usefulLinks = new ArrayList<>();
        usefulLinks.add(new UsefulLinks("Ηλεκτρονική Γραμματεία", "https://submit-academicid.minedu.gov.gr/", R.drawable.secretariat));
        usefulLinks.add(new UsefulLinks("Ακαδημαική Ταυτότητα", "https://submit-academicid.minedu.gov.gr/", R.drawable.student_card));
        usefulLinks.add(new UsefulLinks("Σύστημα Φοιτητών", "https://student.hmu.gr/", R.drawable.students));
        usefulLinks.add(new UsefulLinks("Σελίδα Φοιτητών", "https://www.facebook.com/groups/213104128868246/", R.drawable.fb));
        usefulLinks.add(new UsefulLinks("e-Class", "https://eclass.hmu.gr/", R.drawable.eclass));
        usefulLinks.add(new UsefulLinks("Δήμος Αγ.Νικολάου", "https://www.dimosagn.gr/", R.drawable.dimos));
        usefulLinks.add(new UsefulLinks("Προτεινόμενα Εστιατόρια", "https://www.tripadvisor.com.gr/", R.drawable.tripadvisor));
        usefulLinks.add(new UsefulLinks("Εύδοξος", "https://eudoxus.gr/", R.drawable.eudoxus));
        usefulLinks.add(new UsefulLinks("Edu E-mail Φοιτητή", "http://webmail.edu.hmu.gr/", R.drawable.webmail));
        usefulLinks.add(new UsefulLinks("Events Τμήματος", "https://mst.hmu.gr/news_gr/", R.drawable.events));
        usefulLinks.add(new UsefulLinks("Κ.Τ.Ε.Λ Ηρακλείου - Λασιθίου", "https://www.ktelherlas.gr/", R.drawable.ktel));
        usefulLinks.add(new UsefulLinks("Εφαρμογή Εξάπλωσης Covid-19", "https://mst.hmu.gr/1473-2/", R.drawable.app));
    }

    private void openSchedule() {
        String scheduleUrl = "https://mst.hmu.gr/proptyxiako/%cf%89%cf%81%ce%bf%ce%bb%cf%8c%ce%b3%ce%b9%ce%bf-%cf%80%cf%81%cf%8c%ce%b3%cf%81%ce%b1%ce%bc%ce%bc%ce%b1-%ce%bc%ce%b1%ce%b8%ce%b7%ce%bc%ce%ac%cf%84%cf%89%ce%bd/";
        Intent schedule = new Intent(Intent.ACTION_VIEW, Uri.parse(scheduleUrl));
        startActivity(schedule);
    }
}
