package com.stathis.elmepaunivapp.ui.announcements

import android.content.Intent
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.novoda.merlin.*
import com.stathis.elmepaunivapp.R
import com.stathis.elmepaunivapp.abstraction.ElmepaActivity
import com.stathis.elmepaunivapp.callbacks.AnnouncementClickListener
import com.stathis.elmepaunivapp.databinding.ActivityAnnouncementsBinding
import com.stathis.elmepaunivapp.ui.announcements.model.Announcement
import com.stathis.elmepaunivapp.ui.webview.WebviewActivity
import com.stathis.elmepaunivapp.util.withColor

class AnnouncementsActivity : ElmepaActivity<ActivityAnnouncementsBinding>(R.layout.activity_announcements),AnnouncementClickListener,Connectable,Disconnectable,Bindable {

    private lateinit var viewModel : AnnouncementsViewModel
    private lateinit var merlin : Merlin

    override fun init() {
        viewModel = ViewModelProvider(this).get(AnnouncementsViewModel::class.java)

        merlin = Merlin.Builder().withConnectableCallbacks()
            .withDisconnectableCallbacks()
            .withBindableCallbacks()
            .build(this)

        merlin.registerConnectable(this)
        merlin.registerDisconnectable(this)
        merlin.registerBindable(this)
    }

    override fun startOps() {
        merlin?.let { merlin.bind() }

        supportActionBar?.title = resources.getString(R.string.announcement_header)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

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
        startActivity(Intent(this, WebviewActivity::class.java).putExtra(resources.getString(R.string.url_tag),announcement.url))
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onConnect() = viewModel.refreshData()

    override fun onDisconnect() {
        Snackbar.make(binding.announcementsParent, resources.getString(R.string.no_internet), Snackbar.LENGTH_LONG)
            .withColor(ContextCompat.getColor(this, R.color.orange))
            .show()
    }

    override fun onBind(networkStatus: NetworkStatus?) = when(networkStatus?.isAvailable){
        true -> onConnect()
        else -> onDisconnect()
    }

    override fun onDestroy() {
        super.onDestroy()
        merlin?.let { merlin.unbind() }
    }
}