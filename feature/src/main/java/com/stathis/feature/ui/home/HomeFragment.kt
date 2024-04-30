package com.stathis.feature.ui.home

import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.stathis.core.base.BaseFragment
import com.stathis.core.util.setScreenTitle
import com.stathis.core.util.setupDashboardGridLayout
import com.stathis.core.util.setupItemDecoration
import com.stathis.feature.ui.MainViewModel
import com.stathis.feature.R
import com.stathis.feature.databinding.FragmentHomeBinding
import com.stathis.feature.navigation.NavigationAction
import com.stathis.feature.ui.home.recyclerview.DashboardAdapter
import com.stathis.feature.util.toNavigationAction
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    private val viewModel by viewModels<HomeViewModel>()
    private val activityVM by activityViewModels<MainViewModel>()

    private val adapter = DashboardAdapter { selectedOption ->
        val navAction = selectedOption.type.toNavigationAction()
        activityVM.navigateWithAction(navAction)
    }

    override fun init() {
        setScreenTitle(getString(com.stathis.core.R.string.main_screen_title))

        binding.dashboardRecycler.apply {
            layoutManager = requireContext().setupDashboardGridLayout()
            setupItemDecoration(top = 25, start = 10, end = 10)
            adapter = this@HomeFragment.adapter
        }

        viewModel.fetchDashboardDetails()
    }

    override fun startOps() {
        lifecycleScope.launch {
            viewModel.dashboardDetails.flowWithLifecycle(lifecycle).collect { list ->
                adapter.submitList(list)
            }
        }
    }

    override fun stopOps() {}
}