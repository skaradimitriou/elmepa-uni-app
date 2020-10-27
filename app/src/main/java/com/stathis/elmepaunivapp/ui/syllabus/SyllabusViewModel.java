package com.stathis.elmepaunivapp.ui.syllabus;

import androidx.lifecycle.ViewModel;

import com.stathis.elmepaunivapp.ui.syllabus.model.Semester;
import com.stathis.elmepaunivapp.ui.syllabus.recyclerview.SemesterAdapter;

import java.util.ArrayList;

public class SyllabusViewModel extends ViewModel {

    SemesterAdapter adapter = new SemesterAdapter();
    private ArrayList<Semester> list = new ArrayList<>();

    void populateList(){
        list.add(new Semester("Εξάμηνο Α'"));
        list.add(new Semester("Εξάμηνο Β'"));
        list.add(new Semester("Εξάμηνο Γ'"));
        list.add(new Semester("Εξάμηνο Δ'"));
        list.add(new Semester("Εξάμηνο Ε'"));
        list.add(new Semester("Εξάμηνο ΣΤ'"));
        list.add(new Semester("Εξάμηνο Ζ'"));
        list.add(new Semester("Εξάμηνο Η'"));

        adapter.submitList(list);
    }

}
