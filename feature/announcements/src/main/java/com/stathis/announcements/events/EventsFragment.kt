package com.stathis.announcements.events

import android.os.Bundle
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.stathis.announcements.R
import com.stathis.announcements.databinding.FragmentEventsBinding
import com.stathis.announcements.events.adapter.EventsAdapter
import com.stathis.common.MainViewModel
import com.stathis.common.base.BaseFragment
import com.stathis.common.util.IMAGE
import com.stathis.common.util.PUB_DATE
import com.stathis.common.util.TITLE
import com.stathis.common.util.URL
import com.stathis.common.util.setScreenTitle
import com.stathis.common.util.setupItemDecoration
import com.stathis.model.navigation.NavigationAction
import com.stathis.model.network.NetworkResult
import dagger.hilt.android.AndroidEntryPoint
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
            viewModel.fetchDepartmentEvents(forceUpdate = true)
        }
    }

    override fun startOps() {
        viewModel.fetchDepartmentEvents()

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.events.flowWithLifecycle(lifecycle).collect { result ->
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