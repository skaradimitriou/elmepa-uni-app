package com.stathis.elmepaunivapp.ui.dashboard

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import com.stathis.elmepaunivapp.R
import com.stathis.elmepaunivapp.abstraction.ElmepaActivity
import com.stathis.elmepaunivapp.listeners.new.DashboardClickListener
import com.stathis.elmepaunivapp.ui.chatbot.ChatBotActivity
import com.stathis.elmepaunivapp.ui.dashboard.model.DashboardOption
import com.stathis.elmepaunivapp.ui.department.DepartmentActivity
import com.stathis.elmepaunivapp.ui.professors.ProfessorActivity
import com.stathis.elmepaunivapp.ui.students.StudentsActivity
import com.stathis.elmepaunivapp.ui.webview.WebviewActivity
import kotlinx.android.synthetic.main.activity_dashboard.*

class DashboardActivityKt : ElmepaActivity(R.layout.activity_dashboard) {

    private lateinit var viewModel : DashboardActivityViewModel

    override fun init() {
        viewModel = ViewModelProvider(this).get(DashboardActivityViewModel::class.java)
    }

    override fun startOps() {
        dashboard_options_recview.adapter = viewModel.adapter

        viewModel.bindList(object : DashboardClickListener{
            override fun dashboardItemClicked(option: DashboardOption) {
                when(option.drawable){
                    R.drawable.ic_announcement -> {
                    /*
                        create database for announcements and then enable this
                        startActivity(Intent(this@DashboardActivityKt, AnnouncementActivity::class.java))
                     */
                    }
                    R.drawable.ic_books -> startActivity(Intent(this@DashboardActivityKt, DepartmentActivity::class.java))
                    R.drawable.ic_student -> startActivity(Intent(this@DashboardActivityKt, StudentsActivity::class.java))
                    R.drawable.ic_teacher -> startActivity(Intent(this@DashboardActivityKt, ProfessorActivity::class.java))
                    else -> Unit
                }
            }

            override fun learnMore() {
                startActivity(Intent(this@DashboardActivityKt, WebviewActivity::class.java)
                    .putExtra("URL","https://mst.hmu.gr/ypiresies/mobile-epharmogh-tmhmatos/"))
            }
        })

        fab_chatbot.setOnClickListener{
            startActivity(Intent(this@DashboardActivityKt,ChatBotActivity::class.java))
        }

        about.setOnClickListener{
            viewModel.showDialog(this)
        }
    }

    override fun stopOps(){}
}