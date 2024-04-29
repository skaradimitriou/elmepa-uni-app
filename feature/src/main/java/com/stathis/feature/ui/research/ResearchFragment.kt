package com.stathis.feature.ui.research

import android.os.Bundle
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.stathis.core.base.BaseFragment
import com.stathis.core.util.setupItemDecoration
import com.stathis.feature.ui.MainViewModel
import com.stathis.feature.R
import com.stathis.feature.databinding.FragmentResearchInDeptBinding
import com.stathis.feature.navigation.NavigationAction
import com.stathis.feature.ui.research.recycler.ResearchAdapter
import com.stathis.feature.util.TITLE
import com.stathis.feature.util.URL
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ResearchFragment :
    BaseFragment<FragmentResearchInDeptBinding>(R.layout.fragment_research_in_dept) {

    private val viewModel by viewModels<ResearchViewModel>()
    private val activityVM by activityViewModels<MainViewModel>()

    private val adapter = ResearchAdapter { item ->
        val args = Bundle().apply {
            putString(URL, item.url)
            putString(TITLE, item.name)
        }

        activityVM.navigateWithAction(NavigationAction.WEBVIEW, args)
    }

    override fun init() {
        viewModel.fetchResearchInformation()

        binding.researchRecycler.apply {
            setupItemDecoration(top = 30)
            adapter = this@ResearchFragment.adapter
        }
    }

    override fun startOps() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.data.flowWithLifecycle(lifecycle).collect { list ->
                adapter.submitList(list)
            }
        }
    }

    override fun stopOps() {}
}