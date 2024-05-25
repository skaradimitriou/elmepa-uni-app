package com.stathis.feature.ui.research

import android.os.Bundle
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.stathis.core.MainViewModel
import com.stathis.core.base.BaseFragment
import com.stathis.core.util.setScreenTitle
import com.stathis.core.util.setupItemDecoration
import com.stathis.feature.R
import com.stathis.feature.databinding.FragmentResearchInDeptBinding
import com.stathis.feature.ui.research.recycler.ResearchAdapter
import com.stathis.feature.util.TITLE
import com.stathis.feature.util.URL
import com.stathis.model.navigation.NavigationAction
import com.stathis.model.network.NetworkResult
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ResearchFragment :
    BaseFragment<FragmentResearchInDeptBinding>(R.layout.fragment_research_in_dept) {

    private val viewModel by viewModels<ResearchViewModel>()
    private val activityVM by activityViewModels<MainViewModel>()

    private val adapter = ResearchAdapter { item ->
        val args = Bundle().apply {
            putString(URL, item.openUrl)
            putString(TITLE, item.name)
        }

        activityVM.navigateWithAction(NavigationAction.WEBVIEW, args)
    }

    override fun init() {
        setScreenTitle(getString(com.stathis.core.R.string.research_in_dept))
        viewModel.fetchResearchInformation()

        binding.researchRecycler.apply {
            setupItemDecoration(top = 30)
            adapter = this@ResearchFragment.adapter
        }
    }

    override fun startOps() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.data.flowWithLifecycle(lifecycle).collect { result ->
                when (result) {
                    is NetworkResult.Success -> {
                        adapter.submitList(result.data)
                    }

                    else -> Unit
                }
            }
        }
    }

    override fun stopOps() {}
}