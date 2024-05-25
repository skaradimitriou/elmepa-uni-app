package com.stathis.announcements.events

import android.os.Bundle
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.stathis.announcements.R
import com.stathis.announcements.databinding.FragmentEventsBinding
import com.stathis.announcements.events.adapter.EventsAdapter
import com.stathis.core.MainViewModel
import com.stathis.core.base.BaseFragment
import com.stathis.core.util.TITLE
import com.stathis.core.util.URL
import com.stathis.core.util.setScreenTitle
import com.stathis.core.util.setupItemDecoration
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
            putString(URL, model.url)
            putString(TITLE, model.name)
        }
        activityVM.navigateWithAction(NavigationAction.WEBVIEW, args)
    }

    override fun init() {
        setScreenTitle(getString(com.stathis.core.R.string.events))

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