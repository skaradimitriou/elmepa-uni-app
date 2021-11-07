package com.stathis.elmepaunivapp.ui.syllabus.repos

import com.stathis.elmepaunivapp.ui.syllabus_lessons.model.Lesson

class BusinessLessonsRepo {

    fun getSemesterA(): List<Lesson> {
        return listOf(
            Lesson("Εισαγωγή στην Οικονομική Θεωρία", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","ba"),
            Lesson("Οργάνωση και Διοίκηση Επιχειρήσεων", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","ba"),
            Lesson("Μαθηματική Ανάλυση", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","ba"),
            Lesson("Επιχειρησιακή Επικοινωνία", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 2 ΩΡΕΣ  \nΕΡΓΑΣΤΗΡΙΟ : 2 ΩΡΕΣ","ba"),
            Lesson("Εισαγωγή στην πληροφορική", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","ba"))
    }
    
    fun getSemesterB() : List<Lesson> {
        return listOf(
            Lesson("Πιθανότητες και Στατιστική", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","ba"),
            Lesson("Μάρκετινγκ", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","ba"),
            Lesson("Αρχές Λογιστικής", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","ba"),
            Lesson("Δομημένος Προγραμματισμός", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 2 ΩΡΕΣ  \nΕΡΓΑΣΤΗΡΙΟ : 2 ΩΡΕΣ","ba"),
            Lesson("Γραμμική Άλγεβρα και Διακριτά Μαθηματικά", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","ba"))
    }

    fun getSemesterC() : List<Lesson> {
        return listOf(
            Lesson("Επιχειρησιακή Έρευνα", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","ba"),
            Lesson("Εφαρμοσμένη Στατιστική", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","ba"),
            Lesson("Αντικειμενοστραφής Προγραμματισμός", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 2 ΩΡΕΣ  \nΕΡΓΑΣΤΗΡΙΟ : 2 ΩΡΕΣ","ba"),
            Lesson("Αλγόριθμοι και Δομές Δεδομένων", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","ba"),
            Lesson("Χρηματοοικονομική Λογιστική", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","ba"),  )
    }

    fun getSemesterD() : List<Lesson> {
        return listOf(
            Lesson("Χρηματοοικονομική Διοίκηση", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","ba"),
            Lesson("Λήψη Επιχειρηματικών Αποφάσεων", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","ba"),
            Lesson("Βάσεις Δεδομένων", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 2 ΩΡΕΣ \nΕΡΓΑΣΤΗΡΙΟ : 2 ΩΡΕΣ","ba"),
            Lesson("Μέθοδοι Βελτιστοποίησης", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","ba"),
            Lesson("Σχεδιασμός και Βέλτιστη Εμπειρία Χρήση (UX)", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","ba"),)
    }

    fun getSemesterE() : List<Lesson> {
        return listOf(
            Lesson("Διοίκηση Έργων και Προγραμμάτων", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","ba"),
            Lesson("Διοίκηση Ποιότητας", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","ba"),
            Lesson("Μηχανική Μάθηση", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","ba"),
            Lesson("Επιχειρησιακή Διαδικτύωση και Ηλεκτρονικές Συναλλαγές", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","ba"),
            Lesson("Πληροφοριακά Συστήματα", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","ba"),)
    }

    fun getSemesterF() : List<Lesson> {
        return listOf(
            Lesson("Διαχείριση Ανθρωπίνων Πόρων", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","ba"),
            Lesson("Ηγεσία και Οργανωσιακή Ψυχολογία", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","ba"),
            Lesson("Επιχειρησιακή Πολιτική και Στρατηγική", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","ba"),
            Lesson("Διεθνή Οικονομικά και Αγορές", "Μάθημα Επιλογής", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","ba"),
            Lesson("Δημόσιες Σχέσεις - Επικοινωνία", "Μάθημα Επιλογής", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","ba"),
            Lesson("Διαχείριση και επεξεργασία Μεγάλου όγκου δεδομένων", "Μάθημα Επιλογής", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","ba"),
            Lesson("Συστήματα Διαχείρισης Επιχειρησιακών Πόρων", "Μάθημα Επιλογής", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","ba"),)
    }

    fun getSemesterG() : List<Lesson> {
        return listOf(
            Lesson("Ανάπτυξη Προσωπικών Ικανοτήτων", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","ba"),
            Lesson("Στοχαστική Μοντελοποίηση και Προσομοίωση", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","ba"),
            Lesson("Διοίκηση Παραγωγής και Υπηρεσιών", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","ba"),
            Lesson("Διοίκηση Εφοδιαστικής Αλυσίδας", "Μάθημα Επιλογής", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","ba"),
            Lesson("Ψηφιακή Καινοτομία και Επιχειρηματικότητα", "Μάθημα Επιλογής", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","ba"),
            Lesson("Εμπορικό και Εργατικό Δίκαιο", "Μάθημα Επιλογής", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","ba"),
            Lesson("Ανάλυση Χρονοσειρών", "Μάθημα Επιλογής", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","ba"),
            Lesson("Έμπειρα Συστήματα", "Μάθημα Επιλογής", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","ba"),
            Lesson("Κοινωνική Ευθύνη Επιχειρήσεων και Οργανισμών", "Μάθημα Επιλογής", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","ba"),)
    }

    fun getSemesterH() : List<Lesson> {
        return listOf(
            Lesson("Πτυχιακή Εργασία", "Μάθημα Υποχρεωτικό", " ","ba"),
            Lesson("Επιχειρηματικός Σχεδιασμός", "Μάθημα Υποχρεωτικό", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","ba"),
            Lesson("Πρακτική Άσκηση", "Μάθημα Επιλογής", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","ba"),
            Lesson("Προχωρημένα Συστήματα Διαχείρισης Επιχειρησιακών Πόρων", "Μάθημα Επιλογής", "ΘΕΩΡΙΑ : 2 ΩΡΕΣ  \nΕΡΓΑΣΤΗΡΙΟ : 2 ΩΡΕΣ","ba"),
            Lesson("Χρηματοοικονομική Μηχανική", "Μάθημα Επιλογής", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","ba"),
            Lesson("Θέματα Επιχειρησιακής Έρευνας και Συστημάτων Αποφάσεων", "Μάθημα Επιλογής", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","ba"),
            Lesson("Οπτική και Διερευνητική Ανάλυση Δεδομένων", "Μάθημα Επιλογής", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","ba"),
            Lesson("Σχεδιασμός και Ανάπτυξη Προϊόντων", "Μάθημα Επιλογής", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","ba"),
            Lesson("Προχωρημένα θέματα υπολογιστής Νοημοσύνης", "Μάθημα Επιλογής", "ΘΕΩΡΙΑ : 4 ΩΡΕΣ","ba"),)
    }
}