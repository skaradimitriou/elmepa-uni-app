package com.stathis.feature.research

import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.stathis.core.base.BaseFragment
import com.stathis.core.util.setupItemDecoration
import com.stathis.feature.R
import com.stathis.feature.databinding.FragmentResearchInDeptBinding
import com.stathis.feature.research.recycler.ResearchAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class ResearchFragment :
    BaseFragment<FragmentResearchInDeptBinding>(R.layout.fragment_research_in_dept) {

    private val viewModel by viewModels<ResearchViewModel>()

    private val adapter = ResearchAdapter { item ->
        Timber.d("$item")
        //FIXME: Open in webview inside the app
        //startActivity(Intent(this@ResearchActivity, WebviewActivity::class.java).apply {
//            putExtra(URL, item.url)
//            putExtra(TITLE, item.name)
//        })
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