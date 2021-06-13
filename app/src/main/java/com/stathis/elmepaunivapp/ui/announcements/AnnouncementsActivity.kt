package com.stathis.elmepaunivapp.ui.announcements

import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.stathis.elmepaunivapp.R
import com.stathis.elmepaunivapp.abstraction.ElmepaActivity
import com.stathis.elmepaunivapp.listeners.latest.AnnouncementClickListenerKt
import com.stathis.elmepaunivapp.ui.announcements.model.Announcement
import com.stathis.elmepaunivapp.ui.dashboard.DashboardActivity
import com.stathis.elmepaunivapp.ui.department.DepartmentActivityKt
import com.stathis.elmepaunivapp.ui.professors.ProfessorActivity
import com.stathis.elmepaunivapp.ui.students.StudentsActivityKt
import com.stathis.elmepaunivapp.ui.webview.WebviewActivity
import kotlinx.android.synthetic.main.activity_announcements.*
import kotlinx.android.synthetic.main.activity_announcements.bottom_nav

class AnnouncementsActivity : ElmepaActivity(R.layout.activity_announcements),
    AnnouncementClickListenerKt {

    private lateinit var viewModel : AnnouncementsViewModel

    override fun init() {
        viewModel = ViewModelProvider(this).get(AnnouncementsViewModel::class.java)
    }

    override fun startOps() {
        latestNews_recView.adapter = viewModel.adapter

        viewModel.bindCallback(this)

        swipe_refresh_layout.setOnRefreshListener {
            viewModel.refreshData()
            swipe_refresh_layout.isRefreshing = false
        }

        viewModel.error.observe(this, Observer {
            when(it){
                true -> Toast.makeText(this,"Error getting Data from site", Toast.LENGTH_LONG).show()
                false -> Unit
            }
        })

        viewModel.observeData(this)

        setupBottomNavigation()
    }

    private fun setupBottomNavigation() {
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

    override fun stopOps() {
        viewModel.removeObserver(this)
    }

    override fun onAnnouncementTap(announcement: Announcement) {
        startActivity(Intent(this, WebviewActivity::class.java)
            .putExtra("URL",announcement.url))
    }
}