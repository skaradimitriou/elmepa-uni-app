package com.stathis.elmepaunivapp.ui.students

import com.stathis.elmepaunivapp.R
import com.stathis.elmepaunivapp.abstraction.ElmepaActivity
import com.stathis.elmepaunivapp.databinding.ActivityStudentBinding
import com.stathis.elmepaunivapp.util.setupBar

class StudentActivity : ElmepaActivity<ActivityStudentBinding>(R.layout.activity_student) {

    override fun init() {
        supportActionBar?.setupBar(getString(R.string.dashboard_students))
    }

    override fun startOps() {

    }

    override fun stopOps() {

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}