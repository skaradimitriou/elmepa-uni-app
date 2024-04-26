package com.stathis.feature.announcements

import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.stathis.core.base.BaseFragment
import com.stathis.core.util.setupItemDecoration
import com.stathis.feature.R
import com.stathis.feature.announcements.adapter.AnnouncementsAdapter
import com.stathis.feature.databinding.FragmentAnnouncementsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class AnnouncementsFragment :
    BaseFragment<FragmentAnnouncementsBinding>(R.layout.fragment_announcements) {

    private val viewModel by viewModels<AnnouncementsViewModel>()

    private val adapter = AnnouncementsAdapter { selectedAnnouncement ->
        //FIXME: Open announcement on Web (?)
        Timber.d("$selectedAnnouncement")
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