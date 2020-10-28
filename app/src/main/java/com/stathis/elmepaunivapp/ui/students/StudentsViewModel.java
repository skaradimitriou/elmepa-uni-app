package com.stathis.elmepaunivapp.ui.students;

import androidx.lifecycle.ViewModel;

import com.stathis.elmepaunivapp.R;
import com.stathis.elmepaunivapp.models.DeptFieldsOfStudy;
import com.stathis.elmepaunivapp.ui.students.model.Schedule;
import com.stathis.elmepaunivapp.ui.students.model.StudentItem;
import com.stathis.elmepaunivapp.ui.students.model.UsefulLinks;
import com.stathis.elmepaunivapp.ui.students.recycler.StudentsAdapter;

import java.util.ArrayList;

public class StudentsViewModel extends ViewModel {

    private ArrayList<UsefulLinks> usefulLinks, studentsMatters,fieldsOfStudy = new ArrayList<>();
    private ArrayList<Object> studentItems = new ArrayList<>();
    StudentsAdapter adapter = new StudentsAdapter();

    void createList(){
        adapter.submitList(studentItems);
        getUsefulLinks();
        getStudentMatters();
        getFieldsOfStudy();

        studentItems.add(new Schedule("",""));
        studentItems.add(new StudentItem("Πρόγραμμα Σπουδών",fieldsOfStudy));
        studentItems.add(new StudentItem("Σπουδαστικά θέματα",studentsMatters));
        studentItems.add(new StudentItem("Χρήσιμοι Σύνδεσμοι",usefulLinks));
        adapter.notifyDataSetChanged();

    }

    public ArrayList<UsefulLinks> getFieldsOfStudy() {
        fieldsOfStudy = new ArrayList<>();
        fieldsOfStudy.add(new UsefulLinks("Επιστήμη των Δεδομένων & Τεχνολογίες Πληροφορικής","", R.drawable.data));
        fieldsOfStudy.add(new UsefulLinks("Διοίκηση Επιχειρήσεων & Οργανισμών","", R.drawable.business));
        fieldsOfStudy.add(new UsefulLinks("Ψηφιακό Μάρκετινγκ και Επικοινωνία","", R.drawable.digitalmkt));
        return fieldsOfStudy;
    }

    public ArrayList<UsefulLinks> getStudentMatters() {
        studentsMatters = new ArrayList<>();
        studentsMatters.add(new UsefulLinks("Ακαδημαϊκό Ημερολόγιο","https://mst.hmu.gr/proptyxiako/akadhmaiko-hmerologio/", R.drawable.acadschedule));
        studentsMatters.add(new UsefulLinks("Σύμβουλος Καθηγητής","https://mst.hmu.gr/proptyxiako/symboylos-kathhghths/", R.drawable.mentor));
        studentsMatters.add(new UsefulLinks("Πρόγραμμα Erasmus+","https://mst.hmu.gr/proptyxiako/programma-erasmus-dia-bioy-mathhsh/", R.drawable.erasmus));
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
