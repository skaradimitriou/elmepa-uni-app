package com.stathis.elmepaunivapp.ui.dashboard;

import androidx.lifecycle.ViewModel;

import com.stathis.elmepaunivapp.R;
import com.stathis.elmepaunivapp.ui.dashboard.model.DashboardOption;

import java.util.ArrayList;
import java.util.List;

public class DashboardViewModel extends ViewModel {

    private String title;
    private String aboutText;
    private ArrayList<DashboardOption> dashboardOptions;

    String getTitle() {
        title = "Σχετικά με την εφαρμογή";
        return title;
    }

    String getAboutText() {
        aboutText = "Η εφαρμογή αυτή αναπτύχθηκε το 2020 από τον απόφοιτο του Τμήματος \n" +
                "Στάθη  Καραδημητρίου με την επίβλεψη του Κώστα Παναγιωτάκη, Αναπληρωτή Καθηγητή και Προέδρου του Τμήματος\n" +
                "με σκοπό την εξυπηρέτηση των μελών του Τμήματος και την προώθηση του τμήματος σε υποψήφιους φοιτητές. \n" +
                "Αποτελεί την επόμενη έκδοση της εφαρμογής κινητών που είχε αναπτυχθεί στα πλαίσια της πτυχιακής εργασίας \n" +
                "των φοιτητών του τμήματος Πελοπίδα Κεφαλιανού και Μαρίας Λαγουδάκη το 2017.";
        return aboutText;
    }

    List<DashboardOption> getDashboardOptions(){
        dashboardOptions = new ArrayList<DashboardOption>();
        dashboardOptions.add(new DashboardOption("Ανακοινώσεις", R.drawable.ic_announcement));
        dashboardOptions.add(new DashboardOption("Το Τμήμα", R.drawable.ic_books));
        dashboardOptions.add(new DashboardOption("Φοιτητές", R.drawable.ic_student));
        dashboardOptions.add(new DashboardOption("Προσωπικό", R.drawable.ic_teacher));
        return dashboardOptions;
    }
}
