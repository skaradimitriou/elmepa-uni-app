package com.stathis.elmepaunivapp.ui.syllabus.SyllabusFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.stathis.elmepaunivapp.R;
import com.stathis.elmepaunivapp.ui.syllabus_lessons.model.Lesson;
import com.stathis.elmepaunivapp.listeners.LessonClickListener;
import com.stathis.elmepaunivapp.recyclerviews.LessonsAdapter;

import java.util.ArrayList;

public class Data_Fragment extends Fragment {

    private LessonsAdapter lessonsAdapterA, lessonsAdapterB, lessonsAdapterC, lessonsAdapterD, lessonsAdapterE, lessonsAdapterF, lessonsAdapterG, lessonsAdapterH;
    private RecyclerView a_lessonsRecView, b_lessonsRecView, c_lessonsRecView, d_lessonsRecView, e_lessonsRecView, f_lessonsRecView, g_lessonsRecView, h_lessonsRecView;
    private ArrayList<Lesson> AsemesterList = new ArrayList<>();
    private ArrayList<Lesson> BsemesterList = new ArrayList<>();
    private ArrayList<Lesson> CsemesterList = new ArrayList<>();
    private ArrayList<Lesson> DsemesterList = new ArrayList<>();
    private ArrayList<Lesson> EsemesterList = new ArrayList<>();
    private ArrayList<Lesson> FsemesterList = new ArrayList<>();
    private ArrayList<Lesson> GsemesterList = new ArrayList<>();
    private ArrayList<Lesson> HsemesterList = new ArrayList<>();


    public Data_Fragment() {
        //empty constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_syllabus, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        createList();

        a_lessonsRecView = view.findViewById(R.id.a_lessons_RecView);

        lessonsAdapterA = new LessonsAdapter(AsemesterList, new LessonClickListener() {
            @Override
            public void onLessonClick(Lesson lesson) {

            }

            @Override
            public void onClick(View v) {

            }
        });
        a_lessonsRecView.setAdapter(lessonsAdapterA);
        b_lessonsRecView = view.findViewById(R.id.b_lessons_RecView);

        lessonsAdapterB = new LessonsAdapter(BsemesterList, new LessonClickListener() {
            @Override
            public void onLessonClick(Lesson lesson) {

            }

            @Override
            public void onClick(View v) {

            }
        });
        b_lessonsRecView.setAdapter(lessonsAdapterB);
        c_lessonsRecView = view.findViewById(R.id.c_lessons_RecView);

        lessonsAdapterC = new LessonsAdapter(CsemesterList, new LessonClickListener() {
            @Override
            public void onLessonClick(Lesson lesson) {

            }

            @Override
            public void onClick(View v) {

            }
        });
        c_lessonsRecView.setAdapter(lessonsAdapterC);
        d_lessonsRecView = view.findViewById(R.id.d_lessons_RecView);

        lessonsAdapterD = new LessonsAdapter(DsemesterList, new LessonClickListener() {
            @Override
            public void onLessonClick(Lesson lesson) {

            }

            @Override
            public void onClick(View v) {

            }
        });
        d_lessonsRecView.setAdapter(lessonsAdapterD);
        e_lessonsRecView = view.findViewById(R.id.e_lessons_RecView);

        lessonsAdapterE = new LessonsAdapter(EsemesterList, new LessonClickListener() {
            @Override
            public void onLessonClick(Lesson lesson) {

            }

            @Override
            public void onClick(View v) {

            }
        });
        e_lessonsRecView.setAdapter(lessonsAdapterE);
        // F Semester
        f_lessonsRecView = view.findViewById(R.id.f_lessons_RecView);

        lessonsAdapterF = new LessonsAdapter(FsemesterList, new LessonClickListener() {
            @Override
            public void onLessonClick(Lesson lesson) {

            }

            @Override
            public void onClick(View v) {

            }
        });
        f_lessonsRecView.setAdapter(lessonsAdapterF);
        // G Semester
        g_lessonsRecView = view.findViewById(R.id.g_lessons_RecView);

        lessonsAdapterG = new LessonsAdapter(GsemesterList, new LessonClickListener() {
            @Override
            public void onLessonClick(Lesson lesson) {

            }

            @Override
            public void onClick(View v) {

            }
        });
        g_lessonsRecView.setAdapter(lessonsAdapterG);

        // H Semester
        h_lessonsRecView = view.findViewById(R.id.h_lessons_RecView);

        lessonsAdapterH = new LessonsAdapter(HsemesterList, new LessonClickListener() {
            @Override
            public void onLessonClick(Lesson lesson) {

            }

            @Override
            public void onClick(View v) {

            }
        });
        h_lessonsRecView.setAdapter(lessonsAdapterH);
    }

    private void createList() {
        // A Semester
        AsemesterList.add(new Lesson("Εισαγωγή στην Οικονομική Θεωρία", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","data"));
        AsemesterList.add(new Lesson("Οργάνωση και Διοίκηση Επιχειρήσεων", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","data"));
        AsemesterList.add(new Lesson("Μαθηματική Ανάλυση", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","data"));
        AsemesterList.add(new Lesson("Επιχειρησιακή Επικοινωνία", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 2 ΩΡΕΣ \n ΕΡΓΑΣΤΗΡΙΟ : 2 ΩΡΕΣ","data"));
        AsemesterList.add(new Lesson("Εισαγωγή στην πληροφορική", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","data"));
        // B Semester
        BsemesterList.add(new Lesson("Πιθανότητες και Στατιστική", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","data"));
        BsemesterList.add(new Lesson("Μάρκετινγκ", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","data"));
        BsemesterList.add(new Lesson("Αρχές Λογιστικής", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","data"));
        BsemesterList.add(new Lesson("Δομημένος Προγραμματισμός", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 2 ΩΡΕΣ \n ΕΡΓΑΣΤΗΡΙΟ : 2 ΩΡΕΣ","data"));
        BsemesterList.add(new Lesson("Γραμμική Άλγεβρα και Διακριτά Μαθηματικά", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","data"));
        // C Semester
        CsemesterList.add(new Lesson("Επιχειρησιακή Έρευνα", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","data"));
        CsemesterList.add(new Lesson("Εφαρμοσμένη Στατιστική", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","data"));
        CsemesterList.add(new Lesson("Αντικειμενοστραφής Προγραμματισμός", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 2 ΩΡΕΣ \n ΕΡΓΑΣΤΗΡΙΟ : 2 ΩΡΕΣ","data"));
        CsemesterList.add(new Lesson("Αλγόριθμοι και Δομές Δεδομένων", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","data"));
        CsemesterList.add(new Lesson("Χρηματοοικονομική Λογιστική", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","data"));
        // D Semester
        DsemesterList.add(new Lesson("Χρηματοοικονομική Διοίκηση", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","data"));
        DsemesterList.add(new Lesson("Λήψη Επιχειρηματικών Αποφάσεων", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","data"));
        DsemesterList.add(new Lesson("Βάσεις Δεδομένων", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 2 ΩΡΕΣ \n ΕΡΓΑΣΤΗΡΙΟ : 2 ΩΡΕΣ","data"));
        DsemesterList.add(new Lesson("Μέθοδοι Βελτιστοποίησης", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","data"));
        DsemesterList.add(new Lesson("Σχεδιασμός και Βέλτιστη Εμπειρία Χρήση (UX)", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","data"));
        // E Semester
        EsemesterList.add(new Lesson("Διοίκηση Έργων και Προγραμμάτων", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","data"));
        EsemesterList.add(new Lesson("Διοίκηση Ποιότητας", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","data"));
        EsemesterList.add(new Lesson("Μηχανική Μάθηση", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","data"));
        EsemesterList.add(new Lesson("Επιχειρησιακή Διαδικτύωση και Ηλεκτρονικές Συναλλαγές", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","data"));
        EsemesterList.add(new Lesson("Πληροφοριακά Συστήματα", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","data"));
        // F Semester
        FsemesterList.add(new Lesson("Διαχείριση Ανθρωπίνων Πόρων", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","data"));
        FsemesterList.add(new Lesson("Τεχνολογία Λογισμικού", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","data"));
        FsemesterList.add(new Lesson("Διαχείριση και επεξεργασία Μεγάλου όγκου δεδομένων", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","data"));
        FsemesterList.add(new Lesson("Ηλεκτρονικό Εμπόριο και Εφαρμογές Διαδικτύου", "Μάθημα Επιλογής", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","data"));
        FsemesterList.add(new Lesson("Συστήματα Διαχείρισης Επιχειρησιακών Πόρων", "Μάθημα Επιλογής", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","data"));
        FsemesterList.add(new Lesson("Οργάνωση Υπολογιστών και Λειτουργικά Συστήματα", "Μάθημα Επιλογής", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","data"));
        FsemesterList.add(new Lesson("Σχεδίαση και Ανάπτυξη Κινητών Εφαρμογών", "Μάθημα Επιλογής", "ΘΕΩΡΙΑ : 2 ΩΡΕΣ \n ΕΡΓΑΣΤΗΡΙΟ : 2 ΩΡΕΣ","data"));
        FsemesterList.add(new Lesson("Παραγωγή Ψηφιακού Περιεχομένου", "Μάθημα Επιλογής", "ΘΕΩΡΙΑ : 2 ΩΡΕΣ \n ΕΡΓΑΣΤΗΡΙΟ : 2 ΩΡΕΣ","data"));
        // G Semester
        GsemesterList.add(new Lesson("Ανάπτυξη Προσωπικών Ικανοτήτων", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","data"));
        GsemesterList.add(new Lesson("Επιχειρηματική Αναλυτική", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","data"));
        GsemesterList.add(new Lesson("Στοχαστική Μοντελοποίηση και Προσομοίωση", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","data"));
        GsemesterList.add(new Lesson("Ψηφιακή Καινοτομία και Επιχειρηματικότητα", "Μάθημα Επιλογής", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","data"));
        GsemesterList.add(new Lesson("Ανάλυση Χρονοσειρών", "Μάθημα Επιλογής", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","data"));
        GsemesterList.add(new Lesson("Προχωρημένα Θέματα Βάσεων Δεδομένων", "Μάθημα Επιλογής", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","data"));
        GsemesterList.add(new Lesson("Διοίκηση Εφοδιαστικής Αλυσίδας", "Μάθημα Επιλογής", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","data"));
        GsemesterList.add(new Lesson("Ανάπτυξη Διαδικτυακών και Νεφοϋπολογιστικών Εφαρμογών", "Μάθημα Επιλογής", "ΘΕΩΡΙΑ : 2 ΩΡΕΣ \n ΕΡΓΑΣΤΗΡΙΟ : 2 ΩΡΕΣ","data"));
        // H Semester
        HsemesterList.add(new Lesson("Πτυχιακή Εργασία", "Μάθημα Υποχρεωτικό", " ","data"));
        HsemesterList.add(new Lesson("Επιχειρηματικός Σχεδιασμός", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","data"));
        HsemesterList.add(new Lesson("Πρακτική Άσκηση", "Μάθημα Επιλογής", " ","data"));
        HsemesterList.add(new Lesson("Προχωρημένα Συστήματα Διαχείρισης Επιχειρησιακών Πόρων", "Μάθημα Επιλογής", "ΘΕΩΡΙΑ : 2 ΩΡΕΣ \n ΕΡΓΑΣΤΗΡΙΟ : 2 ΩΡΕΣ","data"));
        HsemesterList.add(new Lesson("Ανάλυση Πολυμεσικών Δεδομένων", "Μάθημα Επιλογής", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","data"));
        HsemesterList.add(new Lesson("Θέματα Επιχειρησιακής Έρευνας και Συστημάτων Αποφάσεων", "Μάθημα Επιλογής", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","data"));
        HsemesterList.add(new Lesson("Οπτική και Διερευνητική Ανάλυση Δεδομένων", "Μάθημα Επιλογής", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","data"));
        HsemesterList.add(new Lesson("Επιχειρηματική Ευφυΐα & Διαχείριση Γνώσης", "Μάθημα Επιλογής", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","data"));
        HsemesterList.add(new Lesson("Προχωρημένα Θέματα Υπολογιστικής Νοημοσύνης", "Μάθημα Επιλογής", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","data"));
    }
}
