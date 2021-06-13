package com.stathis.elmepaunivapp.ui.research

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import com.stathis.elmepaunivapp.R
import com.stathis.elmepaunivapp.abstraction.ElmepaActivity
import com.stathis.elmepaunivapp.ui.dashboard.DashboardActivity
import com.stathis.elmepaunivapp.ui.department.DepartmentActivity
import com.stathis.elmepaunivapp.ui.professors.ProfessorActivity
import com.stathis.elmepaunivapp.ui.students.StudentsActivity
import kotlinx.android.synthetic.main.activity_research_in_dept.*
import kotlinx.android.synthetic.main.activity_research_in_dept.bottom_nav

class ResearchActivity : ElmepaActivity(R.layout.activity_research_in_dept) {

    private lateinit var viewModel : ResearchViewModel

    override fun init() {
        viewModel = ViewModelProvider(this).get(ResearchViewModel::class.java)
    }

    override fun startOps() {
        research_activity_recycler.adapter = viewModel.adapter

        viewModel.createLists()

        bottom_nav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> {
                    startActivity(Intent(this, DashboardActivity::class.java))
                    true
                }
                R.id.nav_students -> {
                    startActivity(Intent(this, StudentsActivity::class.java))
                    true
                }

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

    override fun stopOps() {
        //
    }
}