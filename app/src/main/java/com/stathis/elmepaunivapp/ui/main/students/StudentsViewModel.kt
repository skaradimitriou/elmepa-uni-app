package com.stathis.elmepaunivapp.ui.main.students

import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.stathis.elmepaunivapp.R
import com.stathis.elmepaunivapp.callbacks.ElmepaClickListener
import com.stathis.elmepaunivapp.callbacks.StudentsScreenCallback
import com.stathis.elmepaunivapp.ui.main.students.model.Schedule
import com.stathis.elmepaunivapp.ui.main.students.model.StudentItem
import com.stathis.elmepaunivapp.ui.main.students.model.UsefulLinks
import com.stathis.elmepaunivapp.ui.main.students.recycler.StudentAdapter

class StudentsViewModel(app : Application) : AndroidViewModel(app), ElmepaClickListener {

    private lateinit var callback : StudentsScreenCallback
    val adapter = StudentAdapter(this)
    private val repo = StudentsRepository(app)

    fun bindCallbacks(callback : StudentsScreenCallback) {
        this.callback = callback
    }

    fun createLists() {
        val fieldsOfStudy = listOf(
            UsefulLinks("Επιστήμη των Δεδομένων & Τεχνολογίες Πληροφορικής","", R.drawable.data),
            UsefulLinks("Διοίκηση Επιχειρήσεων & Οργανισμών","", R.drawable.business),
            UsefulLinks("Ψηφιακό Μάρκετινγκ και Επικοινωνία","", R.drawable.digitalmkt))
        
        val studentsMatters = listOf(
            UsefulLinks("Ακαδημαϊκό Ημερολόγιο","https://mst.hmu.gr/proptyxiako/akadhmaiko-hmerologio/", R.drawable.acadschedule),
            UsefulLinks("Σύμβουλος Καθηγητής","https://mst.hmu.gr/proptyxiako/symboylos-kathhghths/", R.drawable.mentor),
            UsefulLinks("Πρόγραμμα Erasmus+","https://mst.hmu.gr/proptyxiako/programma-erasmus-dia-bioy-mathhsh/", R.drawable.erasmus))
    
        val usefulLinks = listOf(
            UsefulLinks("Ηλεκτρονική Γραμματεία", "https://submit-academicid.minedu.gov.gr/", R.drawable.secretariat),
            UsefulLinks("Ακαδημαική Ταυτότητα", "https://submit-academicid.minedu.gov.gr/", R.drawable.student_card),
            UsefulLinks("Σύστημα Φοιτητών", "https://student.hmu.gr/", R.drawable.students),
            UsefulLinks("Σελίδα Φοιτητών", "https://www.facebook.com/groups/213104128868246/", R.drawable.fb),
            UsefulLinks("e-Class", "https://eclass.hmu.gr/", R.drawable.eclass),
            UsefulLinks("Δήμος Αγ.Νικολάου", "https://www.dimosagn.gr/", R.drawable.dimos),
            UsefulLinks("Προτεινόμενα Εστιατόρια", "https://www.tripadvisor.com.gr/", R.drawable.tripadvisor),
            UsefulLinks("Εύδοξος", "https://eudoxus.gr/", R.drawable.eudoxus),
            UsefulLinks("Edu E-mail Φοιτητή", "http://webmail.edu.hmu.gr/", R.drawable.webmail),
            UsefulLinks("Events Τμήματος", "https://mst.hmu.gr/news_gr/", R.drawable.events),
            UsefulLinks("Κ.Τ.Ε.Λ Ηρακλείου - Λασιθίου", "https://www.ktelherlas.gr/", R.drawable.ktel),
            UsefulLinks("Εφαρμογή Εξάπλωσης Covid-19", "https://mst.hmu.gr/1473-2/", R.drawable.app))

        adapter.submitList(listOf(
            Schedule("",""),
            StudentItem("Πρόγραμμα Σπουδών",fieldsOfStudy),
            StudentItem("Σπουδαστικά θέματα",studentsMatters),
            StudentItem("Χρήσιμοι Σύνδεσμοι",usefulLinks)))

    }

    override fun onItemClick(view: View) {
        when(view.tag){
            is Schedule -> callback.openSchedule()
            is UsefulLinks -> {
                val link = view.tag as UsefulLinks
                when(link.name){
                    "Επιστήμη των Δεδομένων & Τεχνολογίες Πληροφορικής" -> callback.openSyllabus(view.tag as UsefulLinks)
                    "Διοίκηση Επιχειρήσεων & Οργανισμών" -> callback.openSyllabus(view.tag as UsefulLinks)
                    "Ψηφιακό Μάρκετινγκ και Επικοινωνία" -> callback.openSyllabus(view.tag as UsefulLinks)
                    else -> callback.openLink(view.tag as UsefulLinks)
                }
            }
        }
    }
}