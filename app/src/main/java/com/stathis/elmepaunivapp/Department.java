package com.stathis.elmepaunivapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.stathis.elmepaunivapp.models.DeptFieldsOfStudy;
import com.stathis.elmepaunivapp.models.DeptPerks;
import com.stathis.elmepaunivapp.models.ProfessorModel;
import com.stathis.elmepaunivapp.models.Programmes;
import com.stathis.elmepaunivapp.models.SocialChannels;
import com.stathis.elmepaunivapp.models.UsefulLinks;
import com.stathis.elmepaunivapp.recyclerview.FieldsAdapter;
import com.stathis.elmepaunivapp.recyclerview.ItemClickListener;
import com.stathis.elmepaunivapp.recyclerview.ProgrammesAdapter;
import com.stathis.elmepaunivapp.recyclerview.SocialChannelAdapter;
import com.stathis.elmepaunivapp.recyclerview.SocialChannelsViewHolder;

import java.util.ArrayList;

import static android.Manifest.permission.CALL_PHONE;

public class Department extends AppCompatActivity {

    private static final int REQUEST_CALL = 1;
    private FloatingActionButton call, mail;
    private RecyclerView fields_recView, programmes_recView, social_recView;
    private FieldsAdapter fieldsAdapter;
    private ProgrammesAdapter programmesAdapter;
    private ArrayList<DeptFieldsOfStudy> fieldsOfStudy;
    private SocialChannelAdapter socialChannelAdapter;
    private ArrayList<Programmes> programmes;
    private ArrayList<DeptPerks> deptPerks;
    private ArrayList<SocialChannels> socialChannels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        //createLists
        createLists();

        call = findViewById(R.id.fab_call);
        mail = findViewById(R.id.fab_mail);

        //rec Views & adapters
        fields_recView = findViewById(R.id.fieldsOfStudy_recView);
        fieldsAdapter = new FieldsAdapter(fieldsOfStudy, new ItemClickListener() {
            @Override
            public void onClick(View v) {}

            @Override
            public void onItemClick(DeptFieldsOfStudy item) {
                switch(item.getName()){
                    case "Επιστήμη των Δεδομένων & Τεχνολογίες Πληροφορικής":
                        //
                    case "Διοίκηση Επιχειρήσεων & Οργανισμών":
                        //
                    case "Ψηφιακό Μάρκετινγκ και Επικοινωνία":
                        //
                };
            }

            @Override
            public void onProgrammesClick(Programmes programmes) {}

            @Override
            public void onProfessorClick(ProfessorModel professorModel) {}

            @Override
            public void onUsefulLinksClick(UsefulLinks usefulLinks) {}


        });
        fields_recView.setAdapter(fieldsAdapter);
        programmes_recView = findViewById(R.id.programmes_recView);
        programmesAdapter = new ProgrammesAdapter(programmes, new ItemClickListener() {

            @Override
            public void onClick(View v) {}

            @Override
            public void onItemClick(DeptFieldsOfStudy item) {}

            @Override
            public void onProgrammesClick(Programmes programmes) {
                switch (programmes.getName()){
                    case "Προπτυχιακές Σπουδές":
                        Intent undergraduates = new Intent(Department.this,UndergraduateProg.class);
                        startActivity(undergraduates);
                        break;
                    case "Μεταπτυχιακά Προγράμματα":
                        Intent postgraduates = new Intent(Department.this,PostgraduateProg.class);
                        startActivity(postgraduates);
                        break;
                    case "Εκπόνηση Διδακτορικού":
                        Intent phd = new Intent(Department.this,PhdProg.class);
                        startActivity(phd);
                }
            }

            @Override
            public void onProfessorClick(ProfessorModel professorModel) {}

            @Override
            public void onUsefulLinksClick(UsefulLinks usefulLinks) {}
        });
        programmes_recView.setAdapter(programmesAdapter);

        social_recView = findViewById(R.id.social_recView);
        socialChannelAdapter = new SocialChannelAdapter(socialChannels);
        social_recView.setAdapter(socialChannelAdapter);

        //Fab Buttons
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callAtSecretaryOffice();
            }
        });
        mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendAnEmailToSecretaryOffice();
            }
        });
        //bottom navigation
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.nav_uni);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent i;
                switch(item.getItemId()){
                    case R.id.nav_home:
                        i = new Intent(Department.this, Dashboard.class);
                        startActivity(i);
                        overridePendingTransition(0, 0);
                        break;
                    case R.id.nav_students:
                        i = new Intent(Department.this, Students.class);
                        startActivity(i);
                        overridePendingTransition(0, 0);
                        break;
                    case R.id.nav_uni:
                        return true;
                    case R.id.nav_search:
                        i = new Intent(Department.this, Professors.class);
                        startActivity(i);
                        overridePendingTransition(0, 0);
                        break;
                }
                return false;
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CALL) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                callAtSecretaryOffice();
            } else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void callAtSecretaryOffice() {
        if (ContextCompat.checkSelfPermission(Department.this,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(Department.this, new String[]{CALL_PHONE}, REQUEST_CALL);
        } else {
            String phone = "tel:2841091103";
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse(phone));
            startActivity(callIntent);
        }
    }

    private void sendAnEmailToSecretaryOffice(){
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"kalarhaki@hmu.gr"});
        try {
            startActivity(Intent.createChooser(i, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(Department.this, "There are no email clients installed.",Toast.LENGTH_SHORT).show();
        }
    }

    private void createLists(){
        fieldsOfStudy = new ArrayList<>();
        fieldsOfStudy.add(new DeptFieldsOfStudy("Επιστήμη των Δεδομένων & Τεχνολογίες Πληροφορικής", R.drawable.data));
        fieldsOfStudy.add(new DeptFieldsOfStudy("Διοίκηση Επιχειρήσεων & Οργανισμών", R.drawable.business));
        fieldsOfStudy.add(new DeptFieldsOfStudy("Ψηφιακό Μάρκετινγκ και Επικοινωνία",R.drawable. digitalmkt));

        programmes = new ArrayList<>();
        programmes.add(new Programmes("Προπτυχιακές Σπουδές", "ΔΙΑΡΚΕΙΑΣ 4 ΕΤΩΝ",R.drawable.ungrad));
        programmes.add(new Programmes("Μεταπτυχιακά Προγράμματα", "ΔΙΑΡΚΕΙΑΣ 2 ΕΤΩΝ",R.drawable.postgrad));
        programmes.add(new Programmes("Εκπόνηση Διδακτορικού", "ΕΛΑΧΙΣΤΗΣ ΔΙΑΡΚΕΙΑΣ 3 ΕΤΩΝ",R.drawable.phd));

        deptPerks = new ArrayList<>();
        socialChannels = new ArrayList<>();
        socialChannels.add(new SocialChannels("Youtube","www.google.com",R.drawable.students));
        socialChannels.add(new SocialChannels("LinkedIn","www.google.com",R.drawable.students));
        socialChannels.add(new SocialChannels("Research Gate","www.google.com",R.drawable.students));
    }

}
