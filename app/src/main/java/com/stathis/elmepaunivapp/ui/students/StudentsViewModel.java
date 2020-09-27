package com.stathis.elmepaunivapp.ui.students;

import androidx.lifecycle.ViewModel;

import com.stathis.elmepaunivapp.R;
import com.stathis.elmepaunivapp.models.DeptFieldsOfStudy;
import com.stathis.elmepaunivapp.ui.students.model.UsefulLinks;

import java.util.ArrayList;

public class StudentsViewModel extends ViewModel {

    private ArrayList<DeptFieldsOfStudy> fieldsOfStudy, studentsMatters;
    private ArrayList<UsefulLinks> usefulLinks;

    public ArrayList<DeptFieldsOfStudy> getFieldsOfStudy() {
        fieldsOfStudy = new ArrayList<>();
        fieldsOfStudy.add(new DeptFieldsOfStudy("Επιστήμη των Δεδομένων & Τεχνολογίες Πληροφορικής", R.drawable.data));
        fieldsOfStudy.add(new DeptFieldsOfStudy("Διοίκηση Επιχειρήσεων & Οργανισμών", R.drawable.business));
        fieldsOfStudy.add(new DeptFieldsOfStudy("Ψηφιακό Μάρκετινγκ και Επικοινωνία", R.drawable.digitalmkt));
        return fieldsOfStudy;
    }

    public ArrayList<DeptFieldsOfStudy> getStudentMatters() {
        studentsMatters = new ArrayList<>();
        studentsMatters.add(new DeptFieldsOfStudy("Ακαδημαϊκό Ημερολόγιο", R.drawable.acadschedule));
        studentsMatters.add(new DeptFieldsOfStudy("Σύμβουλος Καθηγητής", R.drawable.mentor));
        studentsMatters.add(new DeptFieldsOfStudy("Πρόγραμμα Erasmus+", R.drawable.erasmus));
        return studentsMatters;
    }

    public ArrayList<UsefulLinks> getUsefulLinks() {
        usefulLinks = new ArrayList<>();
        usefulLinks.add(new UsefulLinks("Ηλεκτρονική Γραμματεία", "https://submit-academicid.minedu.gov.gr/", R.drawable.secretariat));
        usefulLinks.add(new UsefulLinks("Ακαδημαική Ταυτότητα", "https://submit-academicid.minedu.gov.gr/", R.drawable.student_card));
        usefulLinks.add(new UsefulLinks("Σύστημα Φοιτητών", "https://student.hmu.gr/", R.drawable.students));
        usefulLinks.add(new UsefulLinks("Σελίδα Φοιτητών", "https://www.facebook.com/groups/213104128868246/", R.drawable.fb));
        usefulLinks.add(new UsefulLinks("e-Class", "https://eclass.hmu.gr/", R.drawable.eclass));
        usefulLinks.add(new UsefulLinks("Δήμος Αγ.Νικολάου", "https://www.dimosagn.gr/", R.drawable.dimos));
        usefulLinks.add(new UsefulLinks("Προτεινόμενα Εστιατόρια", "https://www.tripadvisor.com.gr/", R.drawable.tripadvisor));
        usefulLinks.add(new UsefulLinks("Εύδοξος", "https://eudoxus.gr/", R.drawable.eudoxus));
        usefulLinks.add(new UsefulLinks("Edu E-mail Φοιτητή", "http://webmail.edu.hmu.gr/", R.drawable.webmail));
        usefulLinks.add(new UsefulLinks("Events Τμήματος", "https://mst.hmu.gr/news_gr/", R.drawable.events));
        usefulLinks.add(new UsefulLinks("Κ.Τ.Ε.Λ Ηρακλείου - Λασιθίου", "https://www.ktelherlas.gr/", R.drawable.ktel));
        usefulLinks.add(new UsefulLinks("Εφαρμογή Εξάπλωσης Covid-19", "https://mst.hmu.gr/1473-2/", R.drawable.app));
        return usefulLinks;
    }

}
