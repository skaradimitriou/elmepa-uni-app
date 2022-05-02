package com.stathis.elmepaunivapp.ui.announcements

import android.content.Intent
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.novoda.merlin.*
import com.stathis.elmepaunivapp.R
import com.stathis.elmepaunivapp.abstraction.ElmepaActivity
import com.stathis.elmepaunivapp.callbacks.AnnouncementClickListener
import com.stathis.elmepaunivapp.databinding.ActivityAnnouncementsBinding
import com.stathis.elmepaunivapp.model.Announcement
import com.stathis.elmepaunivapp.ui.webview.WebviewActivity
import com.stathis.elmepaunivapp.util.*

class AnnouncementsActivity : ElmepaActivity<ActivityAnnouncementsBinding>(R.layout.activity_announcements),
    AnnouncementClickListener, Connectable, Disconnectable, Bindable {

    private val viewModel: AnnouncementsViewModel by viewModels()
    private lateinit var merlin: Merlin

    override fun init() {
        supportActionBar?.setupBar(getString(R.string.announcement_header))
        binding.viewModel = viewModel

        setupMerlin()
    }

    override fun startOps() {
        viewModel.bindCallback(this)

        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.refreshData()
            binding.swipeRefreshLayout.stopRefresh()
        }

        viewModel.error.observe(this) {
            when (it) {
                true -> Snackbar.make(
                    findViewById(R.id.announcements_parent),
                    resources.getString(R.string.announcements_get_error),
                    Snackbar.LENGTH_LONG
                ).show()
                false -> Unit
            }
        }

        viewModel.observe(this)
    }

    override fun stopOps() {
        viewModel.release(this)
    }

    override fun onAnnouncementTap(announcement: Announcement) {
        startActivity(Intent(this, WebviewActivity::class.java).apply {
            putExtra(URL, announcement.url)
            putExtra(TITLE, ANNOUNCEMENT)
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun setupMerlin() {
        merlin = Merlin.Builder().construct(this)

        merlin.registerConnectable(this)
        merlin.registerDisconnectable(this)
        merlin.registerBindable(this)
        merlin.bind()
    }

    override fun onConnect() = viewModel.refreshData()

    override fun onDisconnect() {
        Snackbar.make(
            binding.announcementsParent,
            resources.getString(R.string.no_internet),
            Snackbar.LENGTH_LONG
        )
            .withColor(ContextCompat.getColor(this, R.color.orange))
            .show()
    }

    override fun onBind(networkStatus: NetworkStatus?) = when (networkStatus?.isAvailable) {
        true -> onConnect()
        else -> onDisconnect()
    }

    override fun onDestroy() {
        super.onDestroy()
        merlin.unbind()
    }
}