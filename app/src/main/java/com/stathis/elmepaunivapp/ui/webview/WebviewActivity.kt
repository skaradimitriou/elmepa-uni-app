package com.stathis.elmepaunivapp.ui.webview

import android.content.Intent
import com.stathis.elmepaunivapp.R
import com.stathis.elmepaunivapp.abstraction.ElmepaActivity
import com.stathis.elmepaunivapp.ui.dashboard.DashboardActivity
import com.stathis.elmepaunivapp.ui.department.DepartmentActivityKt
import com.stathis.elmepaunivapp.ui.professors.ProfessorActivity
import com.stathis.elmepaunivapp.ui.students.StudentsActivityKt
import kotlinx.android.synthetic.main.activity_webview.*
import kotlinx.android.synthetic.main.activity_webview.bottom_nav

class WebviewActivity : ElmepaActivity(R.layout.activity_webview) {


    override fun init() {}

    override fun startOps() {
        val url = intent.getStringExtra("URL")
        webview_window.loadUrl(url)

        bottom_nav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> {
                    startActivity(Intent(this, DashboardActivity::class.java))
                    true
                }
                R.id.nav_students -> {
                    startActivity(Intent(this, StudentsActivityKt::class.java))
                    true
                }

                R.id.nav_uni -> {
                    startActivity(Intent(this, DepartmentActivityKt::class.java))
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