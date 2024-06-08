package com.stathis.news.events

import android.os.Bundle
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.stathis.news.R
import com.stathis.news.databinding.FragmentEventsBinding
import com.stathis.common.MainViewModel
import com.stathis.common.base.BaseFragment
import com.stathis.common.util.IMAGE
import com.stathis.common.util.PUB_DATE
import com.stathis.common.util.TITLE
import com.stathis.common.util.URL
import com.stathis.common.util.setScreenTitle
import com.stathis.common.util.setupItemDecoration
import com.stathis.model.navigation.NavigationAction
import com.stathis.news.events.adapter.EventsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class EventsFragment : BaseFragment<FragmentEventsBinding>(R.layout.fragment_events) {

    private val viewModel by viewModels<EventsViewModel>()
    private val activityVM by activityViewModels<MainViewModel>()

    private val adapter = EventsAdapter { model ->
        val args = Bundle().apply {
            putString(TITLE, model.name)
            putString(IMAGE, model.imageResource)
            putString(URL, model.url)
            putString(PUB_DATE, model.pubDate)
        }
        activityVM.navigateWithAction(NavigationAction.POST_DETAILS, args)
    }

    override fun init() {
        setScreenTitle(getString(com.stathis.common.R.string.events))

        binding.eventsRecycler.apply {
            adapter = this@EventsFragment.adapter
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
            viewModel.events.flowWithLifecycle(lifecycle).collectLatest { result ->
                adapter.submitData(result)
            }
        }
    }

    override fun stopOps() {}
}