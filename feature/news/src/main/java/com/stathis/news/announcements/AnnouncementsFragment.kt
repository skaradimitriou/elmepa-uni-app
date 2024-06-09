package com.stathis.news.announcements

import android.os.Bundle
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.stathis.common.MainViewModel
import com.stathis.common.base.BaseFragment
import com.stathis.common.util.IMAGE
import com.stathis.common.util.PUB_DATE
import com.stathis.common.util.TITLE
import com.stathis.common.util.URL
import com.stathis.common.util.setScreenTitle
import com.stathis.common.util.setupItemDecoration
import com.stathis.model.navigation.NavigationAction
import com.stathis.news.R
import com.stathis.news.announcements.adapter.AnnouncementsAdapter
import com.stathis.news.databinding.FragmentAnnouncementsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AnnouncementsFragment :
    BaseFragment<FragmentAnnouncementsBinding>(R.layout.fragment_announcements) {

    private val viewModel by viewModels<AnnouncementsViewModel>()
    private val activityVM by activityViewModels<MainViewModel>()

    private val adapter = AnnouncementsAdapter { model ->
        val args = Bundle().apply {
            putString(TITLE, model.name)
            putString(IMAGE, model.imageResource)
            putString(URL, model.url)
            putString(PUB_DATE, model.pubDate)
        }
        activityVM.navigateWithAction(NavigationAction.POST_DETAILS, args)
    }

    override fun init() {
        setScreenTitle(getString(com.stathis.common.R.string.announcements))

        binding.announcementsRecycler.apply {
            adapter = this@AnnouncementsFragment.adapter
            setupItemDecoration()
        }

        binding.swipeToRefresh.setOnRefreshListener {
            adapter.refresh()
        }

        adapter.addLoadStateListener { loadState ->
            binding.swipeToRefresh.isRefreshing = loadState.refresh is LoadState.Loading
        }
    }

    override fun startOps() {
        lifecycleScope.launch {
            viewModel.announcements.flowWithLifecycle(lifecycle).collect { result ->
                adapter.submitData(result)
            }
        }
    }

    override fun stopOps() {}
}