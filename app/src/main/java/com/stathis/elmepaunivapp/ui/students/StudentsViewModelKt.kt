package com.stathis.elmepaunivapp.ui.students

import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import com.stathis.elmepaunivapp.R
import com.stathis.elmepaunivapp.listeners.latest.ElmepaClickListener
import com.stathis.elmepaunivapp.listeners.latest.StudentsScreenCallback
import com.stathis.elmepaunivapp.ui.students.model.ScheduleKt
import com.stathis.elmepaunivapp.ui.students.model.StudentItemKt
import com.stathis.elmepaunivapp.ui.students.model.UsefulLinksKt
import com.stathis.elmepaunivapp.ui.students.recycler.StudentAdapterKt

class StudentsViewModelKt : ViewModel(), ElmepaClickListener {

    private lateinit var callback : StudentsScreenCallback
    val adapter = StudentAdapterKt(this)

    fun bindCallbacks(callback : StudentsScreenCallback) {
        this.callback = callback
    }

    fun createLists() {
        val fieldsOfStudy = listOf(
            UsefulLinksKt("Επιστήμη των Δεδομένων & Τεχνολογίες Πληροφορικής","", R.drawable.data),
            UsefulLinksKt("Διοίκηση Επιχειρήσεων & Οργανισμών","", R.drawable.business),
            UsefulLinksKt("Ψηφιακό Μάρκετινγκ και Επικοινωνία","", R.drawable.digitalmkt))
        
        val studentsMatters = listOf(
            UsefulLinksKt("Ακαδημαϊκό Ημερολόγιο","https://mst.hmu.gr/proptyxiako/akadhmaiko-hmerologio/", R.drawable.acadschedule),
            UsefulLinksKt("Σύμβουλος Καθηγητής","https://mst.hmu.gr/proptyxiako/symboylos-kathhghths/", R.drawable.mentor),
            UsefulLinksKt("Πρόγραμμα Erasmus+","https://mst.hmu.gr/proptyxiako/programma-erasmus-dia-bioy-mathhsh/", R.drawable.erasmus))
    
        val usefulLinks = listOf(
            UsefulLinksKt("Ηλεκτρονική Γραμματεία", "https://submit-academicid.minedu.gov.gr/", R.drawable.secretariat),
            UsefulLinksKt("Ακαδημαική Ταυτότητα", "https://submit-academicid.minedu.gov.gr/", R.drawable.student_card),
            UsefulLinksKt("Σύστημα Φοιτητών", "https://student.hmu.gr/", R.drawable.students),
            UsefulLinksKt("Σελίδα Φοιτητών", "https://www.facebook.com/groups/213104128868246/", R.drawable.fb),
            UsefulLinksKt("e-Class", "https://eclass.hmu.gr/", R.drawable.eclass),
            UsefulLinksKt("Δήμος Αγ.Νικολάου", "https://www.dimosagn.gr/", R.drawable.dimos),
            UsefulLinksKt("Προτεινόμενα Εστιατόρια", "https://www.tripadvisor.com.gr/", R.drawable.tripadvisor),
            UsefulLinksKt("Εύδοξος", "https://eudoxus.gr/", R.drawable.eudoxus),
            UsefulLinksKt("Edu E-mail Φοιτητή", "http://webmail.edu.hmu.gr/", R.drawable.webmail),
            UsefulLinksKt("Events Τμήματος", "https://mst.hmu.gr/news_gr/", R.drawable.events),
            UsefulLinksKt("Κ.Τ.Ε.Λ Ηρακλείου - Λασιθίου", "https://www.ktelherlas.gr/", R.drawable.ktel),
            UsefulLinksKt("Εφαρμογή Εξάπλωσης Covid-19", "https://mst.hmu.gr/1473-2/", R.drawable.app))

        adapter.submitList(listOf(
            ScheduleKt("",""),
            StudentItemKt("Πρόγραμμα Σπουδών",fieldsOfStudy),
            StudentItemKt("Σπουδαστικά θέματα",studentsMatters),
            StudentItemKt("Χρήσιμοι Σύνδεσμοι",usefulLinks)))

    }

    override fun onItemClick(view: View) {
        when(view.tag){
            is ScheduleKt -> callback.openSchedule()
            is UsefulLinksKt -> {
                val link = view.tag as UsefulLinksKt
                when(link.name){
                    "Επιστήμη των Δεδομένων & Τεχνολογίες Πληροφορικής" -> callback.openSyllabus(view.tag as UsefulLinksKt)
                    "Διοίκηση Επιχειρήσεων & Οργανισμών" -> callback.openSyllabus(view.tag as UsefulLinksKt)
                    "Ψηφιακό Μάρκετινγκ και Επικοινωνία" -> callback.openSyllabus(view.tag as UsefulLinksKt)
                    else -> callback.openLink(view.tag as UsefulLinksKt)
                }
            }
        }
    }
}