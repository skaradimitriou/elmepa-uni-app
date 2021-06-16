package com.stathis.elmepaunivapp.ui.announcements

import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.stathis.elmepaunivapp.R
import com.stathis.elmepaunivapp.abstraction.ElmepaActivity
import com.stathis.elmepaunivapp.callbacks.AnnouncementClickListener
import com.stathis.elmepaunivapp.ui.announcements.model.Announcement
import com.stathis.elmepaunivapp.ui.webview.WebviewActivity
import kotlinx.android.synthetic.main.activity_announcements.*

class AnnouncementsActivity : ElmepaActivity(R.layout.activity_announcements),
    AnnouncementClickListener {

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

    }

    override fun stopOps() {
        viewModel.removeObserver(this)
    }

    override fun onAnnouncementTap(announcement: Announcement) {
        startActivity(Intent(this, WebviewActivity::class.java)
            .putExtra("URL",announcement.url))
    }
}