package com.stathis.elmepaunivapp.ui.main.students

import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.stathis.elmepaunivapp.R
import com.stathis.elmepaunivapp.abstraction.ElmepaFragment
import com.stathis.elmepaunivapp.callbacks.StudentsScreenCallback
import com.stathis.elmepaunivapp.ui.main.students.model.UsefulLinks
import com.stathis.elmepaunivapp.ui.syllabus.SyllabusActivity
import com.stathis.elmepaunivapp.ui.webview.WebviewActivity
import kotlinx.android.synthetic.main.fragment_students.*

class StudentsFragment : ElmepaFragment(R.layout.fragment_students) {

    private lateinit var viewModel : StudentsViewModel

    override fun init(view : View) {
        viewModel = ViewModelProvider(this).get(StudentsViewModel::class.java)
    }

    override fun startOps() {
        viewModel.createLists()
        students_activity_recycler.adapter = viewModel.adapter

        viewModel.bindCallbacks(object : StudentsScreenCallback {
            override fun openSchedule() {
                startActivity(Intent(requireContext(), WebviewActivity::class.java)
                    .putExtra("URL", "https://mst.hmu.gr/proptyxiako/orologio-programma-mathimaton/"))
            }

            override fun openLink(usefulLinks: UsefulLinks) {
                startActivity(Intent(requireContext(), WebviewActivity::class.java)
                    .putExtra("URL", usefulLinks.url))
            }

            override fun openSyllabus(usefulLink: UsefulLinks) {
                goToSyllabus(usefulLink)
            }
        })

    }

    private fun goToSyllabus(data: UsefulLinks) {
        when(data.name){
            "Επιστήμη των Δεδομένων & Τεχνολογίες Πληροφορικής" -> goToDirection(0)
            "Διοίκηση Επιχειρήσεων & Οργανισμών" -> goToDirection(1)
            "Ψηφιακό Μάρκετινγκ και Επικοινωνία" -> goToDirection(2)
        }
    }

    override fun stopOps() {}

    private fun goToDirection(value : Int){
        startActivity(Intent(requireContext(), SyllabusActivity::class.java)
            .putExtra("userTabChoice", value))
    }
}