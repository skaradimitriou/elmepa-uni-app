package com.stathis.elmepaunivapp.ui.announcements

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.stathis.elmepaunivapp.R
import com.stathis.elmepaunivapp.abstraction.ElmepaActivity
import com.stathis.elmepaunivapp.abstraction.ElmepaBindingActivity
import com.stathis.elmepaunivapp.callbacks.AnnouncementClickListener
import com.stathis.elmepaunivapp.databinding.ActivityAnnouncementsBinding
import com.stathis.elmepaunivapp.ui.announcements.model.Announcement
import com.stathis.elmepaunivapp.ui.webview.WebviewActivity
import kotlinx.android.synthetic.main.activity_announcements.*

class AnnouncementsActivity : ElmepaBindingActivity<ActivityAnnouncementsBinding>(R.layout.activity_announcements),AnnouncementClickListener {

    private lateinit var viewModel : AnnouncementsViewModel

    override fun init() {
        viewModel = ViewModelProvider(this).get(AnnouncementsViewModel::class.java)
    }

    override fun startOps() {
        binding.latestNewsRecView.adapter = viewModel.adapter

        viewModel.bindCallback(this)

        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.refreshData()
            binding.swipeRefreshLayout.isRefreshing = false
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
            .putExtra(resources.getString(R.string.url_tag),announcement.url))
    }
}