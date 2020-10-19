package com.stathis.elmepaunivapp.ui.syllabus;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

import com.stathis.elmepaunivapp.R;
import com.stathis.elmepaunivapp.ui.syllabus.SyllabusFragments.BA_Fragment;
import com.stathis.elmepaunivapp.ui.syllabus.SyllabusFragments.Data_Fragment;
import com.stathis.elmepaunivapp.ui.syllabus.SyllabusFragments.MKT_Fragment;

public class Syllabus extends AppCompatActivity {

    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syllabus);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        Intent getDirection = getIntent();
        name = getDirection.getStringExtra("DIRECTION");

        switch (name) {
            case "ΠΛΗΡΟΦΟΡΙΚΗ":
                FragmentTransaction DataSyllabus = getSupportFragmentManager().beginTransaction();
                DataSyllabus.add(R.id.semester_lessons, new Data_Fragment(), "SemesterFrag");
                DataSyllabus.commit();
                break;
            case "ΔΙΟΙΚΗΣΗ":
                FragmentTransaction BaSyllabus = getSupportFragmentManager().beginTransaction();
                BaSyllabus.add(R.id.semester_lessons, new BA_Fragment(), "BA_Fragment");
                BaSyllabus.commit();
                break;
            case "ΜΑΡΚΕΤΙΝΓΚ":
                FragmentTransaction MktSyllabus = getSupportFragmentManager().beginTransaction();
                MktSyllabus.add(R.id.semester_lessons, new MKT_Fragment(), "MKT_Fragment");
                MktSyllabus.commit();
                break;
        }
    }
}
