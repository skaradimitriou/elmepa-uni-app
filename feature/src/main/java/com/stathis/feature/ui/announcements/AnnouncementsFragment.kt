package com.stathis.feature.ui.announcements

import android.os.Bundle
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.stathis.core.base.BaseFragment
import com.stathis.core.util.setScreenTitle
import com.stathis.core.util.setupItemDecoration
import com.stathis.core.util.startShareIntent
import com.stathis.feature.R
import com.stathis.feature.databinding.FragmentAnnouncementsBinding
import com.stathis.feature.navigation.NavigationAction
import com.stathis.feature.ui.MainViewModel
import com.stathis.feature.ui.announcements.adapter.AnnouncementsAdapter
import com.stathis.feature.ui.announcements.adapter.AnnouncementsCallback
import com.stathis.feature.util.TITLE
import com.stathis.feature.util.URL
import com.stathis.model.announcements.Announcement
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AnnouncementsFragment :
    BaseFragment<FragmentAnnouncementsBinding>(R.layout.fragment_announcements) {

    private val viewModel by viewModels<AnnouncementsViewModel>()
    private val activityVM by activityViewModels<MainViewModel>()

    private val adapter = AnnouncementsAdapter(object : AnnouncementsCallback {
        override fun onAnnouncementTap(model: Announcement) {
            val args = Bundle().apply {
                putString(URL, model.url)
                putString(TITLE, model.name)
            }
            activityVM.navigateWithAction(NavigationAction.WEBVIEW, args)
        }

        override fun onLongAnnouncementTap(model: Announcement) {
            val textBody = String.format(
                getString(com.stathis.core.R.string.announcement_share_body),
                model.name,
                model.url
            )
            startShareIntent(subject = model.name, body = textBody)
        }
    })

    override fun init() {
        setScreenTitle(getString(com.stathis.core.R.string.announcements))

        binding.announcementsRecycler.apply {
            adapter = this@AnnouncementsFragment.adapter
            setupItemDecoration(top = 40, start = 30, end = 30)
        }

        binding.swipeToRefresh.setOnRefreshListener {
            viewModel.fetchAnnouncements(forceUpdate = true)
        }
    }

    override fun startOps() {
        viewModel.fetchAnnouncements()

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.announcements.flowWithLifecycle(lifecycle).collect { announcements ->
                adapter.submitList(announcements)
                binding.swipeToRefresh.isRefreshing = false
            }
        }
    }

    override fun stopOps() {}
}