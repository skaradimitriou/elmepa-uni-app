package com.stathis.elmepaunivapp.ui.syllabus.repos;

import com.stathis.elmepaunivapp.ui.syllabus.model.Lesson;

import java.util.ArrayList;

public class DataLessonsRepo {

    private ArrayList<Lesson> AsemesterList = new ArrayList<>();
    private ArrayList<Lesson> BsemesterList = new ArrayList<>();
    private ArrayList<Lesson> CsemesterList = new ArrayList<>();
    private ArrayList<Lesson> DsemesterList = new ArrayList<>();
    private ArrayList<Lesson> EsemesterList = new ArrayList<>();
    private ArrayList<Lesson> FsemesterList = new ArrayList<>();
    private ArrayList<Lesson> GsemesterList = new ArrayList<>();
    private ArrayList<Lesson> HsemesterList = new ArrayList<>();

    public ArrayList<Lesson> getSemesterA(){
        // A Semester
        AsemesterList.add(new Lesson("Εισαγωγή στην Οικονομική Θεωρία", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","data"));
        AsemesterList.add(new Lesson("Οργάνωση και Διοίκηση Επιχειρήσεων", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","data"));
        AsemesterList.add(new Lesson("Μαθηματική Ανάλυση", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","data"));
        AsemesterList.add(new Lesson("Επιχειρησιακή Επικοινωνία", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 2 ΩΡΕΣ \nΕΡΓΑΣΤΗΡΙΟ : 2 ΩΡΕΣ","data"));
        AsemesterList.add(new Lesson("Εισαγωγή στην πληροφορική", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","data"));
        return AsemesterList;
    }

    public ArrayList<Lesson> getSemesterB(){
        // B Semester
        BsemesterList.add(new Lesson("Πιθανότητες και Στατιστική", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","data"));
        BsemesterList.add(new Lesson("Μάρκετινγκ", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","data"));
        BsemesterList.add(new Lesson("Αρχές Λογιστικής", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","data"));
        BsemesterList.add(new Lesson("Δομημένος Προγραμματισμός", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 2 ΩΡΕΣ \nΕΡΓΑΣΤΗΡΙΟ : 2 ΩΡΕΣ","data"));
        BsemesterList.add(new Lesson("Γραμμική Άλγεβρα και Διακριτά Μαθηματικά", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","data"));
        return BsemesterList;
    }

    public ArrayList<Lesson> getSemesterC(){
        // C Semester
        CsemesterList.add(new Lesson("Επιχειρησιακή Έρευνα", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","data"));
        CsemesterList.add(new Lesson("Εφαρμοσμένη Στατιστική", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","data"));
        CsemesterList.add(new Lesson("Αντικειμενοστραφής Προγραμματισμός", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 2 ΩΡΕΣ \nΕΡΓΑΣΤΗΡΙΟ : 2 ΩΡΕΣ","data"));
        CsemesterList.add(new Lesson("Αλγόριθμοι και Δομές Δεδομένων", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","data"));
        CsemesterList.add(new Lesson("Χρηματοοικονομική Λογιστική", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","data"));
        return CsemesterList;
    }

    public ArrayList<Lesson> getSemesterD(){
        // D Semester
        DsemesterList.add(new Lesson("Χρηματοοικονομική Διοίκηση", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","data"));
        DsemesterList.add(new Lesson("Λήψη Επιχειρηματικών Αποφάσεων", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","data"));
        DsemesterList.add(new Lesson("Βάσεις Δεδομένων", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 2 ΩΡΕΣ \nΕΡΓΑΣΤΗΡΙΟ : 2 ΩΡΕΣ","data"));
        DsemesterList.add(new Lesson("Μέθοδοι Βελτιστοποίησης", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","data"));
        DsemesterList.add(new Lesson("Σχεδιασμός και Βέλτιστη Εμπειρία Χρήση (UX)", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","data"));
        return DsemesterList;
    }

    public ArrayList<Lesson> getSemesterE(){
        // E Semester
        EsemesterList.add(new Lesson("Διοίκηση Έργων και Προγραμμάτων", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","data"));
        EsemesterList.add(new Lesson("Διοίκηση Ποιότητας", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","data"));
        EsemesterList.add(new Lesson("Μηχανική Μάθηση", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","data"));
        EsemesterList.add(new Lesson("Επιχειρησιακή Διαδικτύωση και Ηλεκτρονικές Συναλλαγές", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","data"));
        EsemesterList.add(new Lesson("Πληροφοριακά Συστήματα", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","data"));
        return EsemesterList;
    }

    public ArrayList<Lesson> getSemesterF(){
        // F Semester
        FsemesterList.add(new Lesson("Διαχείριση Ανθρωπίνων Πόρων", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","data"));
        FsemesterList.add(new Lesson("Τεχνολογία Λογισμικού", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","data"));
        FsemesterList.add(new Lesson("Διαχείριση και επεξεργασία Μεγάλου όγκου δεδομένων", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","data"));
        FsemesterList.add(new Lesson("Ηλεκτρονικό Εμπόριο και Εφαρμογές Διαδικτύου", "Μάθημα Επιλογής", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","data"));
        FsemesterList.add(new Lesson("Συστήματα Διαχείρισης Επιχειρησιακών Πόρων", "Μάθημα Επιλογής", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","data"));
        FsemesterList.add(new Lesson("Οργάνωση Υπολογιστών και Λειτουργικά Συστήματα", "Μάθημα Επιλογής", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","data"));
        FsemesterList.add(new Lesson("Σχεδίαση και Ανάπτυξη Κινητών Εφαρμογών", "Μάθημα Επιλογής", "ΘΕΩΡΙΑ : 2 ΩΡΕΣ \nΕΡΓΑΣΤΗΡΙΟ : 2 ΩΡΕΣ","data"));
        FsemesterList.add(new Lesson("Παραγωγή Ψηφιακού Περιεχομένου", "Μάθημα Επιλογής", "ΘΕΩΡΙΑ : 2 ΩΡΕΣ \nΕΡΓΑΣΤΗΡΙΟ : 2 ΩΡΕΣ","data"));
        return FsemesterList;
    }

    public ArrayList<Lesson> getSemesterG(){
        // G Semester
        GsemesterList.add(new Lesson("Ανάπτυξη Προσωπικών Ικανοτήτων", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","data"));
        GsemesterList.add(new Lesson("Επιχειρηματική Αναλυτική", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","data"));
        GsemesterList.add(new Lesson("Στοχαστική Μοντελοποίηση και Προσομοίωση", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","data"));
        GsemesterList.add(new Lesson("Ψηφιακή Καινοτομία και Επιχειρηματικότητα", "Μάθημα Επιλογής", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","data"));
        GsemesterList.add(new Lesson("Ανάλυση Χρονοσειρών", "Μάθημα Επιλογής", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","data"));
        GsemesterList.add(new Lesson("Προχωρημένα Θέματα Βάσεων Δεδομένων", "Μάθημα Επιλογής", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","data"));
        GsemesterList.add(new Lesson("Διοίκηση Εφοδιαστικής Αλυσίδας", "Μάθημα Επιλογής", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","data"));
        GsemesterList.add(new Lesson("Ανάπτυξη Διαδικτυακών και Νεφοϋπολογιστικών Εφαρμογών", "Μάθημα Επιλογής", "ΘΕΩΡΙΑ : 2 ΩΡΕΣ \n ΕΡΓΑΣΤΗΡΙΟ : 2 ΩΡΕΣ","data"));
        return GsemesterList;
    }

    public ArrayList<Lesson> getSemesterH(){
        // H Semester
        HsemesterList.add(new Lesson("Πτυχιακή Εργασία", "Μάθημα Υποχρεωτικό", " ","data"));
        HsemesterList.add(new Lesson("Επιχειρηματικός Σχεδιασμός", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","data"));
        HsemesterList.add(new Lesson("Πρακτική Άσκηση", "Μάθημα Επιλογής", " ","data"));
        HsemesterList.add(new Lesson("Προχωρημένα Συστήματα Διαχείρισης Επιχειρησιακών Πόρων", "Μάθημα Επιλογής", "ΘΕΩΡΙΑ : 2 ΩΡΕΣ \nΕΡΓΑΣΤΗΡΙΟ : 2 ΩΡΕΣ","data"));
        HsemesterList.add(new Lesson("Ανάλυση Πολυμεσικών Δεδομένων", "Μάθημα Επιλογής", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","data"));
        HsemesterList.add(new Lesson("Θέματα Επιχειρησιακής Έρευνας και Συστημάτων Αποφάσεων", "Μάθημα Επιλογής", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","data"));
        HsemesterList.add(new Lesson("Οπτική και Διερευνητική Ανάλυση Δεδομένων", "Μάθημα Επιλογής", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","data"));
        HsemesterList.add(new Lesson("Επιχειρηματική Ευφυΐα & Διαχείριση Γνώσης", "Μάθημα Επιλογής", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","data"));
        HsemesterList.add(new Lesson("Προχωρημένα Θέματα Υπολογιστικής Νοημοσύνης", "Μάθημα Επιλογής", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","data"));
        return HsemesterList;
    }
}
