package com.stathis.elmepaunivapp.ui.dashboard

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.stathis.elmepaunivapp.R
import com.stathis.elmepaunivapp.listeners.DashboardOptionListener
import com.stathis.elmepaunivapp.listeners.activity_listeners.DashboardActivityClickListener
import com.stathis.elmepaunivapp.ui.dashboard.model.DashboardOption
import com.stathis.elmepaunivapp.ui.dashboard.recyclerview.DashboardAdapter

class DashboardActivityViewModel(app : Application) : AndroidViewModel(app), DashboardOptionListener {

    private val context = app
    val adapter = DashboardAdapter(this)
    private lateinit var callback : DashboardActivityClickListener

    fun bindList(callback : DashboardActivityClickListener){
        this.callback = callback

        adapter.submitList(listOf(
            DashboardOption("Ανακοινώσεις", R.drawable.ic_announcement),
            DashboardOption("Το Τμήμα", R.drawable.ic_books),
            DashboardOption("Φοιτητές", R.drawable.ic_student),
            DashboardOption("Προσωπικό", R.drawable.ic_teacher)
        ))
    }

    fun showDialog(context : Context){
        val aboutText = "Η εφαρμογή αυτή αναπτύχθηκε το 2020 από τον απόφοιτο του Τμήματος \n" +
                "Στάθη  Καραδημητρίου με την επίβλεψη του Κώστα Παναγιωτάκη, Αναπληρωτή Καθηγητή και Προέδρου του Τμήματος\n" +
                "με σκοπό την εξυπηρέτηση των μελών του Τμήματος και την προώθηση του τμήματος σε υποψήφιους φοιτητές. \n" +
                "Αποτελεί την επόμενη έκδοση της εφαρμογής κινητών που είχε αναπτυχθεί στα πλαίσια της πτυχιακής εργασίας \n" +
                "των φοιτητών του τμήματος Πελοπίδα Κεφαλιανού και Μαρίας Λαγουδάκη το 2017."

        val builder = MaterialAlertDialogBuilder(context)
        builder.setTitle("Σχετικά με την εφαρμογή")
        builder.setMessage(aboutText)
        builder.setPositiveButton("Μάθε Περισσότερα") { dialog, which ->
            callback.learnMore()
        }
        builder.setNegativeButton("Ακυρο") { dialog, which ->
            dialog.dismiss()
        }.show()
    }

    override fun onDashboardOptionsClickListener(dashboardOption: DashboardOption?) {
        when(dashboardOption?.drawable){
            R.drawable.ic_announcement -> callback.goToAnnouncementScreen(dashboardOption)
            R.drawable.ic_books -> callback.goToDepartmentScreen(dashboardOption)
            R.drawable.ic_student -> callback.goToStudentsScreen(dashboardOption)
            R.drawable.ic_teacher -> callback.goToProfessorScreen(dashboardOption)
            else -> Unit
        }
    }
}