package com.stathis.feature.ui.faq

import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.stathis.core.base.BaseFragment
import com.stathis.core.util.setScreenTitle
import com.stathis.core.util.setupItemDecoration
import com.stathis.feature.R
import com.stathis.feature.databinding.FragmentFaqBinding
import com.stathis.feature.ui.faq.adapter.FaqAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FaqFragment : BaseFragment<FragmentFaqBinding>(R.layout.fragment_faq) {

    private val viewModel by viewModels<FaqViewModel>()

    private val adapter = FaqAdapter()

    override fun init() {
        setScreenTitle(getString(com.stathis.core.R.string.faq_title))

        viewModel.fetchFaqs()

        binding.faqRecycler.apply {
            setupItemDecoration(top = 30, start = 30, end = 30)
            adapter = this@FaqFragment.adapter
        }
    }

    override fun startOps() {
        lifecycleScope.launch {
            viewModel.faq.flowWithLifecycle(lifecycle).collect { list ->
                adapter.submitList(list)
            }
        }
    }

    override fun stopOps() {}
}