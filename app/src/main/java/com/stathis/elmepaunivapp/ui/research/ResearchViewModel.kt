package com.stathis.elmepaunivapp.ui.research

import android.view.View
import androidx.lifecycle.ViewModel
import com.stathis.elmepaunivapp.R
import com.stathis.elmepaunivapp.listeners.latest.ElmepaClickListener
import com.stathis.elmepaunivapp.ui.research.model.ResearchModel
import com.stathis.elmepaunivapp.ui.research.recycler.ResearchAdapter
import com.stathis.elmepaunivapp.ui.students.model.UsefulLinksKt

class ResearchViewModel : ViewModel(), ElmepaClickListener {

    val adapter = ResearchAdapter(this)

    fun createLists() {
        val researchItemsList = listOf(
            UsefulLinksKt(
                "Ινστιτούτο Οικονομικής Ανάλυσης",
                "https://mst.hmu.gr/ereuna/institoyto-oikonomikhs-analyshs-epicheirhmatikothtas-kai-toyrismoy/",
                R.drawable.institute
            ),
            UsefulLinksKt(
                "Ερευνητικά Επιτεύγματα",
                "https://mst.hmu.gr/ereuna/ereynhtika-epiteygmata/",
                R.drawable.achievements
            ),
            UsefulLinksKt(
                "Δημοσιεύσεις",
                "https://mst.hmu.gr/ereuna/dhmosieyseis/",
                R.drawable.papers
            ),
            UsefulLinksKt(
                "Στατιστικά Στοιχεία",
                "https://mst.hmu.gr/ereuna/statistika-stoicheia/",
                R.drawable.analytics
            )
        )

        val researchLabList = listOf(
            UsefulLinksKt(
                "Εργαστήριο Διοικητικής Οικονομικής και Συστημάτων Αποφάσεων",
                "https://mst.hmu.gr/ereuna/adeds/",
                R.drawable.lab
            ),
            UsefulLinksKt(
                "Εργαστήριο Επιστήμης Δεδομένων, Πολυμέσων και Μοντελοποίησης",
                "https://mst.hmu.gr/ereuna/datalab/",
                R.drawable.lab
            ),
            UsefulLinksKt(
                "Εργαστήριο Ηλεκτρονικής Επιχειρηματικής Ευφυΐας",
                "https://www.e-bilab.gr/",
                R.drawable.lab
            )
        )

        adapter.submitList(
            listOf(
                ResearchModel("Έρευνα στο Τμήμα", researchLabList),
                ResearchModel("Έρευνητικά Εργαστήρια", researchItemsList)
            )
        )
    }

    override fun onItemClick(view: View) {
        when(view.tag){
            /*
            Implement click events later on. They should open the webview activity
             */
        }
    }


}
