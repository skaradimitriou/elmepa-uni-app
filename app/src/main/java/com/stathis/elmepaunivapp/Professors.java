package com.stathis.elmepaunivapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.SearchView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.stathis.elmepaunivapp.models.ProfessorModel;
import com.stathis.elmepaunivapp.recyclerview.RecAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Professors extends AppCompatActivity {

    private ArrayList<ProfessorModel> professors;
    private RecAdapter adapter;
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
        adapter = new RecAdapter(professors);
        recyclerView.setAdapter(adapter);

        //bottom navigation & listener
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.nav_search);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent i;
                switch(item.getItemId()){
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

    private void filter(String text){
        ArrayList<ProfessorModel> filteredList = new ArrayList<>();
        for (ProfessorModel item : professors) {
            if (item.getFullName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        adapter.filterList(filteredList);
    }

    private void createProfessorList(){
        professors = new ArrayList<>();
        professors.add(new ProfessorModel("Παπαδάκης Στέλιος", "spap@hmu.gr"));
        professors.add(new ProfessorModel("Παναγιωτάκης Κώστας", "cpanag@hmu.gr"));
        professors.add(new ProfessorModel("Μαστοράκης Γιώργος", "gmastorakis@hmu.gr"));
        professors.add(new ProfessorModel("Κοπανάκης Γιάννης", "kopanakis@hmu.gr"));
        professors.add(new ProfessorModel("Δημοτίκαλης Γιάννης", "jdim@hmu.gr"));
        professors.add(new ProfessorModel("Λεμονάκης Χρήστος", "lemonakis@hmu.gr"));
        professors.add(new ProfessorModel("Περακάκης Μάνος", "mperakakis@hmu.gr"));
        professors.add(new ProfessorModel("Αικατερινίδης Ιωάννης", "aikaterinidis@gmail.com"));
        professors.add(new ProfessorModel("Αστρουλάκης Νικόλαος", "n.astroulakis@gmail.com"));
        professors.add(new ProfessorModel("Βάρδας Ιωάννης", "vardasg@hmu.gr"));
        professors.add(new ProfessorModel("Βασιλειάδης Γεώργιος", "gvasil@hmu.gr"));
        professors.add(new ProfessorModel("Κοκκινάκης Εμμανουήλ", "manoskokkinakis@yahoo.gr"));
        professors.add(new ProfessorModel("Κόττη Εύη", "kottievi@hmu.gr"));
        professors.add(new ProfessorModel("Μαρκάκη Μαρία", "mmarkaki@hmu.gr"));
        professors.add(new ProfessorModel("Μεραμβελιωτάκης Γεώργιος", "gmeramv@hmu.gr"));
        professors.add(new ProfessorModel("Μπάλλας Παναγιώτης", "ballas@hmu.gr"));
        professors.add(new ProfessorModel("Σκουλουδάκης Εμμανουήλ", "Εskououdakis@hmu.gr"));
        professors.add(new ProfessorModel("Σχοινιωτάκης Νικόλαος", "freemarkos@yahoo.gr"));
        professors.add(new ProfessorModel("Τριχάς Νικόλαος", "ntrihas@hmu.gr"));
        professors.add(new ProfessorModel("Τσιλιμπώκος Κωνσταντίνος", "kostsil@hotmail.com"));
        professors.add(new ProfessorModel("Φανουργιάκης Ιωάννης", "jfanourgiakis@yahoo.com"));
        professors.add(new ProfessorModel("Φαφαλιός Παύλος", "fafalios@ics.forth.gr"));
        professors.add(new ProfessorModel("Αρακαδάκης Α. Γεώργιος", "arakadakisjr@hmu.gr"));
        professors.add(new ProfessorModel("Καμπέλη Κωνσταντίνα", "nantia.kampeli@gmail.com"));
        professors.add(new ProfessorModel("Καπανταϊδάκης Ιωάννης", "jkapad@csd.uoc.gr"));
        professors.add(new ProfessorModel("Μπατζανακάκη Ελένη", "eleni@candiafinance.gr"));
        professors.add(new ProfessorModel("Περονικολής Μιχαήλ", "m.peronikolis@yahoo.com"));
        professors.add(new ProfessorModel("Πετράκης Νικόλαος", "nickpetran@yahoo.gr"));
        professors.add(new ProfessorModel("Σφακιανάκης Θεόδωρος", "tmsfakia@hmu.gr"));
        professors.add(new ProfessorModel("Ταβλαδάκη Δέσποινα", "dtavladaki@hmu.gr"));
    }

}
