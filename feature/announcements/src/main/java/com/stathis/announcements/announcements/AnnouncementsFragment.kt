package com.stathis.announcements.announcements

import android.os.Bundle
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.stathis.announcements.R
import com.stathis.announcements.announcements.adapter.AnnouncementsAdapter
import com.stathis.announcements.announcements.adapter.AnnouncementsCallback
import com.stathis.announcements.databinding.FragmentAnnouncementsBinding
import com.stathis.common.MainViewModel
import com.stathis.common.base.BaseFragment
import com.stathis.common.util.IMAGE
import com.stathis.common.util.PUB_DATE
import com.stathis.common.util.TITLE
import com.stathis.common.util.URL
import com.stathis.common.util.setScreenTitle
import com.stathis.common.util.setupItemDecoration
import com.stathis.common.util.startShareIntent
import com.stathis.model.announcements.Announcement
import com.stathis.model.navigation.NavigationAction
import com.stathis.model.network.NetworkResult
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
                putString(TITLE, model.name)
                putString(IMAGE, model.imageResource)
                putString(URL, model.url)
                putString(PUB_DATE, model.pubDate)
            }
            activityVM.navigateWithAction(NavigationAction.POST_DETAILS, args)
        }

        override fun onLongAnnouncementTap(model: Announcement) {
            val textBody = String.format(
                getString(com.stathis.common.R.string.announcement_share_body),
                model.name,
                model.url
            )
            startShareIntent(subject = model.name, body = textBody)
        }
    })

    override fun init() {
        setScreenTitle(getString(com.stathis.common.R.string.announcements))

        binding.announcementsRecycler.apply {
            adapter = this@AnnouncementsFragment.adapter
            setupItemDecoration()
        }

        binding.swipeToRefresh.setOnRefreshListener {
            viewModel.fetchAnnouncements(forceUpdate = true)
        }
    }

    override fun startOps() {
        viewModel.fetchAnnouncements()

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.announcements.flowWithLifecycle(lifecycle).collect { result ->
                when (result) {
                    is NetworkResult.Loading -> {
                        adapter.submitList(result.data)
                    }

                    is NetworkResult.Success -> {
                        adapter.submitList(result.data)
                        binding.swipeToRefresh.isRefreshing = false
                    }

                    else -> Unit
                }
            }
        }
    }

    override fun stopOps() {}
}