package com.stathis.elmepaunivapp.ui.students

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import com.stathis.elmepaunivapp.R
import com.stathis.elmepaunivapp.abstraction.ElmepaActivity
import com.stathis.elmepaunivapp.listeners.latest.StudentsScreenCallback
import com.stathis.elmepaunivapp.ui.dashboard.DashboardActivityKt
import com.stathis.elmepaunivapp.ui.department.DepartmentActivity
import com.stathis.elmepaunivapp.ui.professors.ProfessorActivity
import com.stathis.elmepaunivapp.ui.students.model.UsefulLinksKt
import com.stathis.elmepaunivapp.ui.syllabus.SyllabusActivity
import com.stathis.elmepaunivapp.ui.webview.WebviewActivity
import kotlinx.android.synthetic.main.activity_students.*
import kotlinx.android.synthetic.main.activity_webview.*
import kotlinx.android.synthetic.main.activity_webview.bottom_nav

class StudentsActivityKt : ElmepaActivity(R.layout.activity_students) {

    private lateinit var viewModel : StudentsViewModelKt

    override fun init() {
        viewModel = ViewModelProvider(this).get(StudentsViewModelKt::class.java)
    }

    override fun startOps() {
        viewModel.createLists()
        students_activity_recycler.adapter = viewModel.adapter

        viewModel.bindCallbacks(object : StudentsScreenCallback{
            override fun openSchedule() {
                startActivity(Intent(this@StudentsActivityKt, WebviewActivity::class.java)
                    .putExtra("URL", "https://mst.hmu.gr/proptyxiako/orologio-programma-mathimaton/"))
            }

            override fun openLink(usefulLinks: UsefulLinksKt) {
                startActivity(Intent(this@StudentsActivityKt, WebviewActivity::class.java)
                    .putExtra("URL", usefulLinks.url))
            }

            override fun openSyllabus(usefulLink: UsefulLinksKt) {
                goToSyllabus(usefulLink)
            }
        })

        setupBottomNavigation()
    }

    private fun goToSyllabus(data: UsefulLinksKt) {
        when(data.name){
            "Επιστήμη των Δεδομένων & Τεχνολογίες Πληροφορικής" -> {
                startActivity(Intent(this, SyllabusActivity::class.java)
                    .putExtra("userTabChoice", 0))
            }
            "Διοίκηση Επιχειρήσεων & Οργανισμών" -> {
                startActivity(Intent(this, SyllabusActivity::class.java)
                    .putExtra("userTabChoice", 1))
            }
            "Ψηφιακό Μάρκετινγκ και Επικοινωνία" -> {
                startActivity(Intent(this, SyllabusActivity::class.java)
                    .putExtra("userTabChoice", 2))
            }
        }
    }

    private fun setupBottomNavigation() {
        bottom_nav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> {
                    startActivity(Intent(this, DashboardActivityKt::class.java))
                    true
                }
                R.id.nav_students -> true

                R.id.nav_uni -> {
                    startActivity(Intent(this, DepartmentActivity::class.java))
                    true
                }

                R.id.nav_search -> {
                    startActivity(Intent(this, ProfessorActivity::class.java))
                    true
                }

                else -> false
            }
        }
    }

    override fun stopOps() {}
}