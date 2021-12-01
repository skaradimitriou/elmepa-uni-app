package com.stathis.elmepaunivapp.ui.announcements

import android.content.Intent
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.stathis.elmepaunivapp.R
import com.stathis.elmepaunivapp.abstraction.ElmepaActivity
import com.stathis.elmepaunivapp.callbacks.AnnouncementClickListener
import com.stathis.elmepaunivapp.ui.announcements.model.Announcement
import com.stathis.elmepaunivapp.ui.webview.WebviewActivity
import kotlinx.android.synthetic.main.activity_announcements.*

class AnnouncementsActivity : ElmepaActivity(R.layout.activity_announcements),AnnouncementClickListener {

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
                true -> Snackbar.make(findViewById(R.id.announcements_parent),resources.getString(R.string.announcements_get_error),Snackbar.LENGTH_LONG).show()
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