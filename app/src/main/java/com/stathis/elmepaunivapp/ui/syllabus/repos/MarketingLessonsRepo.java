package com.stathis.elmepaunivapp.ui.syllabus.repos;

import com.stathis.elmepaunivapp.ui.syllabus.model.Lesson;
import com.stathis.elmepaunivapp.ui.syllabus_lessons.model.LessonKt;

import java.util.ArrayList;

public class MarketingLessonsRepo {

    private ArrayList<Lesson> AsemesterList = new ArrayList<>();
    private ArrayList<Lesson> BsemesterList = new ArrayList<>();
    private ArrayList<Lesson> CsemesterList = new ArrayList<>();
    private ArrayList<Lesson> DsemesterList = new ArrayList<>();
    private ArrayList<Lesson> EsemesterList = new ArrayList<>();
    private ArrayList<Lesson> FsemesterList = new ArrayList<>();
    private ArrayList<Lesson> GsemesterList = new ArrayList<>();
    private ArrayList<Lesson> HsemesterList = new ArrayList<>();

    public ArrayList<Lesson> getSemesterA() {
        // A Semester
        AsemesterList.add(new Lesson("Εισαγωγή στην Οικονομική Θεωρία", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ", "mkt"));
        AsemesterList.add(new Lesson("Οργάνωση και Διοίκηση Επιχειρήσεων", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ", "mkt"));
        AsemesterList.add(new Lesson("Μαθηματική Ανάλυση", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ", "mkt"));
        AsemesterList.add(new Lesson("Επιχειρησιακή Επικοινωνία", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 2 ΩΡΕΣ \nΕΡΓΑΣΤΗΡΙΟ : 2 ΩΡΕΣ", "mkt"));
        AsemesterList.add(new Lesson("Εισαγωγή στην πληροφορική", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ", "mkt"));
        return AsemesterList;
    }

    public ArrayList<Lesson> getSemesterB() {
        // B Semester
        BsemesterList.add(new Lesson("Πιθανότητες και Στατιστική", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ", "mkt"));
        BsemesterList.add(new Lesson("Μάρκετινγκ", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ", "mkt"));
        BsemesterList.add(new Lesson("Αρχές Λογιστικής", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ", "mkt"));
        BsemesterList.add(new Lesson("Δομημένος Προγραμματισμός", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 2 ΩΡΕΣ \nΕΡΓΑΣΤΗΡΙΟ : 2 ΩΡΕΣ", "mkt"));
        BsemesterList.add(new Lesson("Γραμμική Άλγεβρα και Διακριτά Μαθηματικά", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ", "mkt"));
        return BsemesterList;
    }

    public ArrayList<Lesson> getSemesterC() {
        CsemesterList.add(new Lesson("Επιχειρησιακή Έρευνα", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ", "mkt"));
        CsemesterList.add(new Lesson("Εφαρμοσμένη Στατιστική", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ", "mkt"));
        CsemesterList.add(new Lesson("Αντικειμενοστραφής Προγραμματισμός", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 2 ΩΡΕΣ \nΕΡΓΑΣΤΗΡΙΟ : 2 ΩΡΕΣ", "mkt"));
        CsemesterList.add(new Lesson("Αλγόριθμοι και Δομές Δεδομένων", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ", "mkt"));
        CsemesterList.add(new Lesson("Χρηματοοικονομική Λογιστική", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ", "mkt"));
        return CsemesterList;
    }

    public ArrayList<Lesson> getSemesterD() {
        // D Semester
        DsemesterList.add(new Lesson("Χρηματοοικονομική Διοίκηση", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ", "mkt"));
        DsemesterList.add(new Lesson("Λήψη Επιχειρηματικών Αποφάσεων", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ", "mkt"));
        DsemesterList.add(new Lesson("Βάσεις Δεδομένων", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 2 ΩΡΕΣ \nΕΡΓΑΣΤΗΡΙΟ : 2 ΩΡΕΣ", "mkt"));
        DsemesterList.add(new Lesson("Μέθοδοι Βελτιστοποίησης", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ", "mkt"));
        DsemesterList.add(new Lesson("Σχεδιασμός και Βέλτιστη Εμπειρία Χρήση (UX)", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ", "mkt"));
        return DsemesterList;
    }

    public ArrayList<Lesson> getSemesterE() {
        // E Semester
        EsemesterList.add(new Lesson("Διοίκηση Έργων και Προγραμμάτων", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ", "mkt"));
        EsemesterList.add(new Lesson("Διοίκηση Ποιότητας", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ", "mkt"));
        EsemesterList.add(new Lesson("Μηχανική Μάθηση", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ", "mkt"));
        EsemesterList.add(new Lesson("Επιχειρησιακή Διαδικτύωση και Ηλεκτρονικές Συναλλαγές", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ", "mkt"));
        EsemesterList.add(new Lesson("Πληροφοριακά Συστήματα", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ", "mkt"));
        return EsemesterList;
    }

    public ArrayList<Lesson> getSemesterF() {
        // F Semester
        FsemesterList.add(new Lesson("Διαχείριση Ανθρωπίνων Πόρων", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ", "mkt"));
        FsemesterList.add(new Lesson("Συμπεριφορά Καταναλωτή", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ", "mkt"));
        FsemesterList.add(new Lesson("Παραγωγή Ψηφιακού Περιεχομένου", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 2 ΩΡΕΣ \nΕΡΓΑΣΤΗΡΙΟ : 2 ΩΡΕΣ", "mkt"));
        FsemesterList.add(new Lesson("Ηλεκτρονικό Εμπόριο και Εφαρμογές Διαδικτύου", "Μάθημα Επιλογής", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ", "mkt"));
        FsemesterList.add(new Lesson("Συστήματα Διαχείρισης Επιχειρησιακών Πόρων", "Μάθημα Επιλογής", "ΘΕΩΡΙΑ : 4 ΩΡΕΣΩΡΕΣ", "mkt"));
        FsemesterList.add(new Lesson("Διαχείριση και επεξεργασία Μεγάλου όγκου δεδομένων", "Μάθημα Επιλογής", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ", "mkt"));
        FsemesterList.add(new Lesson("Σχεδίαση και Ανάπτυξη Κινητών Εφαρμογών", "Μάθημα Επιλογής", "ΘΕΩΡΙΑ : 2 ΩΡΕΣ \nΕΡΓΑΣΤΗΡΙΟ : 2 ΩΡΕΣ", "mkt"));
        FsemesterList.add(new Lesson("Δημόσιες Σχέσεις - Επικοινωνία", "Μάθημα Επιλογής", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ", "mkt"));
        FsemesterList.add(new Lesson("Ηγεσία και Οργανωσιακή Ψυχολογία", "Μάθημα Επιλογής", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ", "mkt"));
        return FsemesterList;
    }

    public ArrayList<Lesson> getSemesterG() {
        // G Semester
        GsemesterList.add(new Lesson("Ανάπτυξη Προσωπικών Ικανοτήτων", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ", "mkt"));
        GsemesterList.add(new Lesson("Διαφημιστική Εκστρατεία και Ψηφιακό Μάρκετινγκ", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ", "mkt"));
        GsemesterList.add(new Lesson("Ψηφιακή Καινοτομία και Επιχειρηματικότητα", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ", "mkt"));
        GsemesterList.add(new Lesson("Επιχειρηματική Αναλυτική", "Μάθημα Επιλογής", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ", "mkt"));
        GsemesterList.add(new Lesson("Ανάλυση Δεδομένων Μάρκετινγκ", "Μάθημα Επιλογής", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ", "mkt"));
        GsemesterList.add(new Lesson("Κοινωνική Ευθύνη Επιχειρήσεων και Οργανισμών", "Μάθημα Επιλογής", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ", "mkt"));
        GsemesterList.add(new Lesson("Σχεδιασμός Δημιουργικού και Διαφημιστικών Μηνυμάτων", "Μάθημα Επιλογής", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ", "mkt"));
        GsemesterList.add(new Lesson("Διοίκηση Εφοδιαστικής Αλυσίδας", "Μάθημα Επιλογής", "ΘΕΩΡΙΑ : 2 ΩΡΕΣ \nΕΡΓΑΣΤΗΡΙΟ : 2 ΩΡΕΣ", "mkt"));
        GsemesterList.add(new Lesson("Ανάπτυξη Διαδικτυακών και\n" +
                "Νεφοϋπολογιστικών Εφαρμογών", "Μάθημα Επιλογής", "ΘΕΩΡΙΑ : 2 ΩΡΕΣ \n ΕΡΓΑΣΤΗΡΙΟ : 4 ΩΡΕΣ", "mkt"));
        return GsemesterList;
    }

    public ArrayList<Lesson> getSemesterH() {
        // H Semester
        HsemesterList.add(new Lesson("Πτυχιακή Εργασία", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ", "mkt"));
        HsemesterList.add(new Lesson("Επιχειρηματικός Σχεδιασμός", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ", "mkt"));
        HsemesterList.add(new Lesson("Πρακτική Άσκηση", "Μάθημα Επιλογής", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ", "mkt"));
        HsemesterList.add(new Lesson("Προχωρημένα Συστήματα Διαχείρισης Επιχειρησιακών Πόρων", "Μάθημα Επιλογής", "ΘΕΩΡΙΑ : 2 ΩΡΕΣ \nΕΡΓΑΣΤΗΡΙΟ : 2 ΩΡΕΣ", "mkt"));
        HsemesterList.add(new Lesson("Τρισδιάστατος Σχεδιασμός και Κινούμενο Σχέδιο", "Μάθημα Επιλογής", "ΘΕΩΡΙΑ : 2 ΩΡΕΣ \nΕΡΓΑΣΤΗΡΙΟ : 2 ΩΡΕΣ", "mkt"));
        HsemesterList.add(new Lesson("Θέματα Επιχειρησιακής Έρευνας και Συστημάτων Αποφάσεων", "Μάθημα Επιλογής", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ", "mkt"));
        HsemesterList.add(new Lesson("Οπτική και Διερευνητική Ανάλυση Δεδομένων", "Μάθημα Επιλογής", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ", "mkt"));
        HsemesterList.add(new Lesson("Νέες Τεχνολογίες & Μάρκετινγκ", "Μάθημα Επιλογής", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ", "mkt"));
        HsemesterList.add(new Lesson("Σχεδιασμός και Ανάπτυξη Προϊόντων", "Μάθημα Επιλογής", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ", "mkt"));
        return HsemesterList;
    }
}
