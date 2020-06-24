package com.stathis.elmepaunivapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.stathis.elmepaunivapp.models.DeptFieldsOfStudy;
import com.stathis.elmepaunivapp.models.ProfessorModel;
import com.stathis.elmepaunivapp.models.Programmes;
import com.stathis.elmepaunivapp.models.UsefulLinks;
import com.stathis.elmepaunivapp.recyclerview.ItemClickListener;
import com.stathis.elmepaunivapp.recyclerview.ProfessorAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Professors extends AppCompatActivity {

    private ArrayList<ProfessorModel> professors;
    private ProfessorAdapter adapter;
    private RecyclerView recyclerView;
    private EditText search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professors);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        //Search in recview
        search = findViewById(R.id.search_action);
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });
        //creates a list of professors
        createProfessorList();

        //sorting professor list ascending
        Collections.sort(professors, new Comparator<ProfessorModel>() {
            @Override
            public int compare(ProfessorModel o1, ProfessorModel o2) {
                return o1.getFullName().compareTo(o2.getFullName());
            }
        });
        //creates recview & adapters
        recyclerView = findViewById(R.id.recyclerView);
        adapter = new ProfessorAdapter(professors, new ItemClickListener() {
            @Override
            public void onItemClick(DeptFieldsOfStudy item) {
            }

            @Override
            public void onProgrammesClick(Programmes programmes) {
            }

            @Override
            public void onProfessorClick(ProfessorModel professorModel) {
                showDialogue(professorModel);
            }

            @Override
            public void onUsefulLinksClick(UsefulLinks usefulLinks) {
            }

            @Override
            public void onClick(View v) {
            }
        });
        recyclerView.setAdapter(adapter);

        //bottom navigation & listener
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.nav_search);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent i;
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        i = new Intent(Professors.this, Dashboard.class);
                        startActivity(i);
                        overridePendingTransition(0, 0);
                        break;
                    case R.id.nav_students:
                        i = new Intent(Professors.this, Students.class);
                        startActivity(i);
                        overridePendingTransition(0, 0);
                        break;
                    case R.id.nav_uni:
                        i = new Intent(Professors.this, Department.class);
                        startActivity(i);
                        overridePendingTransition(0, 0);
                        break;
                    case R.id.nav_search:
                        return true;
                }
                return false;
            }
        });
    }

    private void filter(String text) {
        ArrayList<ProfessorModel> filteredList = new ArrayList<>();
        for (ProfessorModel item : professors) {
            if (item.getFullName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        adapter.filterList(filteredList);
    }

    private void showDialogue(final ProfessorModel professorModel) {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(Professors.this);
        builder.setTitle("Νέο e-mail");
        if (professorModel.getGender().equals("male")) {
            builder.setMessage("Είσαι σίγουρος πως θέλεις να στείλεις e-mail στον κ. " + professorModel.getFullName() + ";");
        } else if (professorModel.getGender().equals("female")) {
            builder.setMessage("Είσαι σίγουρος πως θέλεις να στείλεις e-mail στην κ. " + professorModel.getFullName() + ";");
        }

        builder.setPositiveButton("Ναι", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                sendEmailToProfessor(professorModel);
            }
        });
        builder.setNegativeButton("Άκυρο", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //
            }
        });
        builder.show();
    }

    private void sendEmailToProfessor(ProfessorModel professorModel) {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL, new String[]{professorModel.getEmail()});
        try {
            startActivity(Intent.createChooser(i, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(Professors.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }
    }

    private void createProfessorList() {
        professors = new ArrayList<>();
        professors.add(new ProfessorModel("Παπαδάκης Στέλιος", "spap@hmu.gr", "male"));
        professors.add(new ProfessorModel("Παναγιωτάκης Κώστας", "cpanag@hmu.gr", "male"));
        professors.add(new ProfessorModel("Μαστοράκης Γιώργος", "gmastorakis@hmu.gr", "male"));
        professors.add(new ProfessorModel("Κοπανάκης Γιάννης", "kopanakis@hmu.gr", "male"));
        professors.add(new ProfessorModel("Δημοτίκαλης Γιάννης", "jdim@hmu.gr", "male"));
        professors.add(new ProfessorModel("Λεμονάκης Χρήστος", "lemonakis@hmu.gr", "male"));
        professors.add(new ProfessorModel("Περακάκης Μάνος", "mperakakis@hmu.gr", "male"));
        professors.add(new ProfessorModel("Αικατερινίδης Ιωάννης", "aikaterinidis@gmail.com", "male"));
        professors.add(new ProfessorModel("Αστρουλάκης Νικόλαος", "n.astroulakis@gmail.com", "male"));
        professors.add(new ProfessorModel("Βάρδας Ιωάννης", "vardasg@hmu.gr", "male"));
        professors.add(new ProfessorModel("Βασιλειάδης Γεώργιος", "gvasil@hmu.gr", "male"));
        professors.add(new ProfessorModel("Κοκκινάκης Εμμανουήλ", "manoskokkinakis@yahoo.gr", "male"));
        professors.add(new ProfessorModel("Κόττη Εύη", "kottievi@hmu.gr", "female"));
        professors.add(new ProfessorModel("Μαρκάκη Μαρία", "mmarkaki@hmu.gr", "female"));
        professors.add(new ProfessorModel("Μεραμβελιωτάκης Γεώργιος", "gmeramv@hmu.gr", "male"));
        professors.add(new ProfessorModel("Μπάλλας Παναγιώτης", "ballas@hmu.gr", "male"));
        professors.add(new ProfessorModel("Σκουλουδάκης Εμμανουήλ", "Εskououdakis@hmu.gr", "male"));
        professors.add(new ProfessorModel("Σχοινιωτάκης Νικόλαος", "freemarkos@yahoo.gr", "male"));
        professors.add(new ProfessorModel("Τριχάς Νικόλαος", "ntrihas@hmu.gr", "male"));
        professors.add(new ProfessorModel("Τσιλιμπώκος Κωνσταντίνος", "kostsil@hotmail.com", "male"));
        professors.add(new ProfessorModel("Φανουργιάκης Ιωάννης", "jfanourgiakis@yahoo.com", "male"));
        professors.add(new ProfessorModel("Φαφαλιός Παύλος", "fafalios@ics.forth.gr", "male"));
        professors.add(new ProfessorModel("Αρακαδάκης Α. Γεώργιος", "arakadakisjr@hmu.gr", "male"));
        professors.add(new ProfessorModel("Καμπέλη Κωνσταντίνα", "nantia.kampeli@gmail.com", "female"));
        professors.add(new ProfessorModel("Καπανταϊδάκης Ιωάννης", "jkapad@csd.uoc.gr", "male"));
        professors.add(new ProfessorModel("Μπατζανακάκη Ελένη", "eleni@candiafinance.gr", "female"));
        professors.add(new ProfessorModel("Περονικολής Μιχαήλ", "m.peronikolis@yahoo.com", "male"));
        professors.add(new ProfessorModel("Πετράκης Νικόλαος", "nickpetran@yahoo.gr", "male"));
        professors.add(new ProfessorModel("Σφακιανάκης Θεόδωρος", "tmsfakia@hmu.gr", "male"));
        professors.add(new ProfessorModel("Ταβλαδάκη Δέσποινα", "dtavladaki@hmu.gr", "female"));
    }

}
