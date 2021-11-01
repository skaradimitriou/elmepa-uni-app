package com.stathis.elmepaunivapp.ui.main.professors.repo

import androidx.lifecycle.MutableLiveData
import com.stathis.elmepaunivapp.ui.main.professors.model.Professor
import java.util.*

class ProfessorRepository {

    val professors = MutableLiveData<List<Professor>>()
    val professorList = listOf(
        Professor("Παπαδάκης Στέλιος", "spap@hmu.gr", "male", "Παπαδάκη Στέλιο"),
        Professor("Παναγιωτάκης Κώστας", "cpanag@hmu.gr", "male", "Παναγιωτάκη Κώστα") ,
        Professor("Μαστοράκης Γιώργος", "gmastorakis@hmu.gr", "male", "Μαστοράκη Γιώργο"),
        Professor("Κοπανάκης Γιάννης", "kopanakis@hmu.gr", "male", "Κοπανάκη Γιάννη"),
        Professor("Δημοτίκαλης Γιάννης", "jdim@hmu.gr", "male", "Δημοτίκαλη Γιάννη"),
        Professor("Λεμονάκης Χρήστος", "lemonakis@hmu.gr", "male", "Λεμονάκη Χρήστο"),
        Professor("Περακάκης Μάνος", "mperakakis@hmu.gr", "male", "Περακάκη Μάνο"),
        Professor("Αικατερινίδης Ιωάννης", "aikaterinidis@gmail.com", "male", "Αικατερινίδη Ιωάννη"),
        Professor("Αστρουλάκης Νικόλαος", "n.astroulakis@gmail.com", "male", "Αστρουλάκη Νικόλαο"),
        Professor("Βάρδας Ιωάννης", "vardasg@hmu.gr", "male", "Βάρδα Ιωάννη"),
        Professor("Βασιλειάδης Γεώργιος", "gvasil@hmu.gr", "male", "Βασιλειάδη Γεώργιο"),
        Professor("Κοκκινάκης Εμμανουήλ", "manoskokkinakis@yahoo.gr", "male", "Κοκκινάκη Εμμανουήλ"),
        Professor("Κόττη Εύη", "kottievi@hmu.gr", "female", "Κόττη Εύη"),
        Professor("Μαρκάκη Μαρία", "mmarkaki@hmu.gr", "female", "Μαρκάκη Μαρία"),
        Professor("Μεραμβελιωτάκης Γεώργιος", "gmeramv@hmu.gr", "male", "Μεραμβελιωτάκη Γεώργιο"),
        Professor("Μπάλλας Παναγιώτης", "ballas@hmu.gr", "male", "Μπάλλα Παναγιώτη"),
        Professor("Σκουλουδάκης Εμμανουήλ", "Εskououdakis@hmu.gr", "male", "Σκουλουδάκη Εμμανουήλ"),
        Professor("Σχοινιωτάκης Νικόλαος", "freemarkos@yahoo.gr", "male", "Σχοινιωτάκη Νικόλαο"),
        Professor("Τριχάς Νικόλαος", "ntrihas@hmu.gr", "male", "Τριχά Νικόλαο"),
        Professor("Τσιλιμπώκος Κωνσταντίνος", "kostsil@hotmail.com", "male", "Τσιλιμπώκο Κωνσταντίνο"),
        Professor("Φανουργιάκης Ιωάννης", "jfanourgiakis@yahoo.com", "male", "Φανουργιάκη Ιωάννη"),
        Professor("Φαφαλιός Παύλος", "fafalios@ics.forth.gr", "male", "Φαφαλιό Παύλο"),
        Professor("Αρακαδάκης Α. Γεώργιος", "arakadakisjr@hmu.gr", "male", "Αρακαδάκη Α. Γεώργιο"),
        Professor("Καμπέλη Κωνσταντίνα", "nantia.kampeli@gmail.com", "female", "Καμπέλη Κωνσταντίνα"),
        Professor("Καπανταϊδάκης Ιωάννης", "jkapad@csd.uoc.gr", "male", "Καπανταϊδάκη Ιωάννη"),
        Professor("Μπατζανακάκη Ελένη", "eleni@candiafinance.gr", "female", "Μπατζανακάκη Ελένη"),
        Professor("Περονικολής Μιχαήλ", "m.peronikolis@yahoo.com", "male", "Περονικολή Μιχαήλ"),
        Professor("Πετράκης Νικόλαος", "nickpetran@yahoo.gr", "male", "Πετράκη Νικόλαο"),
        Professor("Σφακιανάκης Θεόδωρος", "tmsfakia@hmu.gr", "male", "Σφακιανάκη Θεόδωρο"),
        Professor("Ταβλαδάκη Δέσποινα", "dtavladaki@hmu.gr", "female", "Ταβλαδάκη Δέσποινα"),
        Professor("Καλαρχάκη Αικατερίνη", "kalarhaki@hmu.gr", "female", "Καλαρχάκη Αικατερίνη"),
        Professor("Καροφύλλου Ειρήνη", "karofillou@hmu.gr", "female", "Καροφύλλου Ειρήνη"),
        Professor("Παρθύμου Γεωργία", "gparth@hmu.gr", "female", "Παρθύμου Γεωργία"),
        Professor("Ματζαράκης Γεώργιος", "matzarakis@hmu.gr", "female", "Ματζαράκη Γεώργιο"),
        Professor("Καλαμπόκη Εύα - Μαρία", "matzarakis@hmu.gr", "female", "Καλαμπόκη Εύα - Μαρία"),
        Professor("Μαρκάκη Σμαράγδα", "sm.markaki@hmu.gr", "female", "Μαρκάκη Σμαράγδα"),
        Professor("Καροφυλλάκης Κανάκης", "kanakis@hmu.gr", "male", "Καροφυλλάκη Κανάκη")
    )

    fun getProfessors(){
        Collections.sort(professorList) { p0, p1 ->
            p0?.fullName!!.compareTo(p1!!.fullName)
        }

        professors.value = professorList
    }

    fun filter(text: String) {
        val filteredList = arrayListOf<Professor>()
        for (item in professorList) {
            if (item.fullName.toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item)
            }
        }
        professors.value = filteredList
    }
}