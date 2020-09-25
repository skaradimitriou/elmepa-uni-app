package com.stathis.elmepaunivapp.ui.professors;

import androidx.lifecycle.ViewModel;

import com.stathis.elmepaunivapp.ui.professors.model.ProfessorModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ProfessorsViewModel extends ViewModel {

    private ArrayList<ProfessorModel> professors = new ArrayList<>();

    ArrayList<ProfessorModel> createProfessorList() {
        professors.add(new ProfessorModel("Παπαδάκης Στέλιος", "spap@hmu.gr", "male", "Παπαδάκη Στέλιο"));
        professors.add(new ProfessorModel("Παναγιωτάκης Κώστας", "cpanag@hmu.gr", "male", "Παναγιωτάκη Κώστα"));
        professors.add(new ProfessorModel("Μαστοράκης Γιώργος", "gmastorakis@hmu.gr", "male", "Μαστοράκη Γιώργο"));
        professors.add(new ProfessorModel("Κοπανάκης Γιάννης", "kopanakis@hmu.gr", "male", "Κοπανάκη Γιάννη"));
        professors.add(new ProfessorModel("Δημοτίκαλης Γιάννης", "jdim@hmu.gr", "male", "Δημοτίκαλη Γιάννη"));
        professors.add(new ProfessorModel("Λεμονάκης Χρήστος", "lemonakis@hmu.gr", "male", "Λεμονάκη Χρήστο"));
        professors.add(new ProfessorModel("Περακάκης Μάνος", "mperakakis@hmu.gr", "male", "Περακάκη Μάνο"));
        professors.add(new ProfessorModel("Αικατερινίδης Ιωάννης", "aikaterinidis@gmail.com", "male", "Αικατερινίδη Ιωάννη"));
        professors.add(new ProfessorModel("Αστρουλάκης Νικόλαος", "n.astroulakis@gmail.com", "male", "Αστρουλάκη Νικόλαο"));
        professors.add(new ProfessorModel("Βάρδας Ιωάννης", "vardasg@hmu.gr", "male", "Βάρδα Ιωάννη"));
        professors.add(new ProfessorModel("Βασιλειάδης Γεώργιος", "gvasil@hmu.gr", "male", "Βασιλειάδη Γεώργιο"));
        professors.add(new ProfessorModel("Κοκκινάκης Εμμανουήλ", "manoskokkinakis@yahoo.gr", "male", "Κοκκινάκη Εμμανουήλ"));
        professors.add(new ProfessorModel("Κόττη Εύη", "kottievi@hmu.gr", "female", "Κόττη Εύη"));
        professors.add(new ProfessorModel("Μαρκάκη Μαρία", "mmarkaki@hmu.gr", "female", "Μαρκάκη Μαρία"));
        professors.add(new ProfessorModel("Μεραμβελιωτάκης Γεώργιος", "gmeramv@hmu.gr", "male", "Μεραμβελιωτάκη Γεώργιο"));
        professors.add(new ProfessorModel("Μπάλλας Παναγιώτης", "ballas@hmu.gr", "male", "Μπάλλα Παναγιώτη"));
        professors.add(new ProfessorModel("Σκουλουδάκης Εμμανουήλ", "Εskououdakis@hmu.gr", "male", "Σκουλουδάκη Εμμανουήλ"));
        professors.add(new ProfessorModel("Σχοινιωτάκης Νικόλαος", "freemarkos@yahoo.gr", "male", "Σχοινιωτάκη Νικόλαο"));
        professors.add(new ProfessorModel("Τριχάς Νικόλαος", "ntrihas@hmu.gr", "male", "Τριχά Νικόλαο"));
        professors.add(new ProfessorModel("Τσιλιμπώκος Κωνσταντίνος", "kostsil@hotmail.com", "male", "Τσιλιμπώκο Κωνσταντίνο"));
        professors.add(new ProfessorModel("Φανουργιάκης Ιωάννης", "jfanourgiakis@yahoo.com", "male", "Φανουργιάκη Ιωάννη"));
        professors.add(new ProfessorModel("Φαφαλιός Παύλος", "fafalios@ics.forth.gr", "male", "Φαφαλιό Παύλο"));
        professors.add(new ProfessorModel("Αρακαδάκης Α. Γεώργιος", "arakadakisjr@hmu.gr", "male", "Αρακαδάκη Α. Γεώργιο"));
        professors.add(new ProfessorModel("Καμπέλη Κωνσταντίνα", "nantia.kampeli@gmail.com", "female", "Καμπέλη Κωνσταντίνα"));
        professors.add(new ProfessorModel("Καπανταϊδάκης Ιωάννης", "jkapad@csd.uoc.gr", "male", "Καπανταϊδάκη Ιωάννη"));
        professors.add(new ProfessorModel("Μπατζανακάκη Ελένη", "eleni@candiafinance.gr", "female", "Μπατζανακάκη Ελένη"));
        professors.add(new ProfessorModel("Περονικολής Μιχαήλ", "m.peronikolis@yahoo.com", "male", "Περονικολή Μιχαήλ"));
        professors.add(new ProfessorModel("Πετράκης Νικόλαος", "nickpetran@yahoo.gr", "male", "Πετράκη Νικόλαο"));
        professors.add(new ProfessorModel("Σφακιανάκης Θεόδωρος", "tmsfakia@hmu.gr", "male", "Σφακιανάκη Θεόδωρο"));
        professors.add(new ProfessorModel("Ταβλαδάκη Δέσποινα", "dtavladaki@hmu.gr", "female", "Ταβλαδάκη Δέσποινα"));
        professors.add(new ProfessorModel("Καλαρχάκη Αικατερίνη", "kalarhaki@hmu.gr", "female", "Καλαρχάκη Αικατερίνη"));
        professors.add(new ProfessorModel("Καροφύλλου Ειρήνη", "karofillou@hmu.gr", "female", "Καροφύλλου Ειρήνη"));
        professors.add(new ProfessorModel("Παρθύμου Γεωργία", "gparth@hmu.gr", "female", "Καροφύλλου Ειρήνη"));
        professors.add(new ProfessorModel("Ματζαράκης Γεώργιος", "matzarakis@hmu.gr", "female", "Καροφύλλου Ειρήνη"));
        return professors;
    }
}
