package com.stathis.feature.ui.announcements

import android.os.Bundle
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.stathis.core.base.BaseFragment
import com.stathis.core.util.setupItemDecoration
import com.stathis.feature.ui.MainViewModel
import com.stathis.feature.R
import com.stathis.feature.ui.announcements.adapter.AnnouncementsAdapter
import com.stathis.feature.databinding.FragmentAnnouncementsBinding
import com.stathis.feature.navigation.NavigationAction
import com.stathis.feature.util.TITLE
import com.stathis.feature.util.URL
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AnnouncementsFragment :
    BaseFragment<FragmentAnnouncementsBinding>(R.layout.fragment_announcements) {

    private val viewModel by viewModels<AnnouncementsViewModel>()
    private val activityVM by activityViewModels<MainViewModel>()

    private val adapter = AnnouncementsAdapter { selectedAnnouncement ->
        val args = Bundle().apply {
            putString(URL, selectedAnnouncement.url)
            putString(TITLE, selectedAnnouncement.name)
        }
        activityVM.navigateWithAction(NavigationAction.WEBVIEW, args)
    }

    override fun init() {
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