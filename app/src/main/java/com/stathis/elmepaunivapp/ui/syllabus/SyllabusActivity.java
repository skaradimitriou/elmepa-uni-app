package com.stathis.elmepaunivapp.ui.syllabus;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.stathis.elmepaunivapp.R;
import com.stathis.elmepaunivapp.models.Lesson;
import com.stathis.elmepaunivapp.ui.syllabus.SyllabusFragments.BA_Fragment;
import com.stathis.elmepaunivapp.ui.syllabus.SyllabusFragments.Data_Fragment;
import com.stathis.elmepaunivapp.ui.syllabus.SyllabusFragments.MKT_Fragment;
import com.stathis.elmepaunivapp.ui.syllabus.model.Semester;
import com.stathis.elmepaunivapp.ui.syllabus.recyclerview.SemesterAdapter;

import java.util.ArrayList;

public class SyllabusActivity extends AppCompatActivity {

    private String name;
    private RecyclerView recyclerView;
    private SemesterAdapter adapter;
    private ArrayList<Semester> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syllabus);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

//        Intent getDirection = getIntent();
//        name = getDirection.getStringExtra("DIRECTION");

//        switch (name) {
//            case "ΠΛΗΡΟΦΟΡΙΚΗ":
//                FragmentTransaction DataSyllabus = getSupportFragmentManager().beginTransaction();
//                DataSyllabus.add(R.id.semester_lessons, new Data_Fragment(), "SemesterFrag");
//                DataSyllabus.commit();
//                break;
//            case "ΔΙΟΙΚΗΣΗ":
//                FragmentTransaction BaSyllabus = getSupportFragmentManager().beginTransaction();
//                BaSyllabus.add(R.id.semester_lessons, new BA_Fragment(), "BA_Fragment");
//                BaSyllabus.commit();
//                break;
//            case "ΜΑΡΚΕΤΙΝΓΚ":
//                FragmentTransaction MktSyllabus = getSupportFragmentManager().beginTransaction();
//                MktSyllabus.add(R.id.semester_lessons, new MKT_Fragment(), "MKT_Fragment");
//                MktSyllabus.commit();
//                break;
//        }

        list.add(new Semester("Εξάμηνο Α"));
        list.add(new Semester("Εξάμηνο Α"));
        list.add(new Semester("Εξάμηνο Α"));
        list.add(new Semester("Εξάμηνο Α"));
        list.add(new Semester("Εξάμηνο Α"));
        list.add(new Semester("Εξάμηνο Α"));
        list.add(new Semester("Εξάμηνο Α"));

        recyclerView = findViewById(R.id.syllabus_recycler);
        adapter = new SemesterAdapter();
        adapter.submitList(list);
        recyclerView.setAdapter(adapter);
    }
}
