package com.stathis.support.ui.faq

import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.stathis.core.base.BaseFragment
import com.stathis.core.util.setScreenTitle
import com.stathis.core.util.setupItemDecoration
import com.stathis.model.network.NetworkResult
import com.stathis.support.R
import com.stathis.support.databinding.FragmentFaqBinding
import com.stathis.support.ui.faq.adapter.FaqAdapter
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
            setupItemDecoration()
            adapter = this@FaqFragment.adapter
        }
    }

    override fun startOps() {
        lifecycleScope.launch {
            viewModel.faq.flowWithLifecycle(lifecycle).collect { result ->
                when (result) {
                    is NetworkResult.Loading -> {
                        adapter.submitList(result.data)
                    }

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