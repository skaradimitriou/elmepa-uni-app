package com.stathis.elmepaunivapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.stathis.elmepaunivapp.SyllabusFragments.BA_Fragment;
import com.stathis.elmepaunivapp.SyllabusFragments.Data_Fragment;
import com.stathis.elmepaunivapp.SyllabusFragments.MKT_Fragment;

public class Syllabus extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syllabus);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        Intent getDirection = getIntent();
        String name = getDirection.getStringExtra("DIRECTION");

        switch(name){
            case "Επιστήμη των Δεδομένων & Τεχνολογίες Πληροφορικής":
                FragmentTransaction DataSyllabus = getSupportFragmentManager().beginTransaction();
                DataSyllabus.add(R.id.semester_lessons, new Data_Fragment(), "SemesterFrag");
                DataSyllabus.commit();
                break;
            case "Διοίκηση Επιχειρήσεων & Οργανισμών":
                FragmentTransaction BaSyllabus = getSupportFragmentManager().beginTransaction();
                BaSyllabus.add(R.id.semester_lessons, new BA_Fragment(), "BA_Fragment");
                BaSyllabus.commit();
                break;
            case "Ψηφιακό Μάρκετινγκ και Επικοινωνία":
                FragmentTransaction MktSyllabus = getSupportFragmentManager().beginTransaction();
                MktSyllabus.add(R.id.semester_lessons, new MKT_Fragment(), "MKT_Fragment");
                MktSyllabus.commit();
                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }
}
