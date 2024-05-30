package com.stathis.support.ui.about

import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.stathis.core.base.BaseFragment
import com.stathis.core.util.setScreenTitle
import com.stathis.core.util.setupItemDecoration
import com.stathis.support.R
import com.stathis.support.databinding.FragmentAboutAppBinding
import com.stathis.support.ui.about.adapter.AboutAppAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AboutAppFragment : BaseFragment<FragmentAboutAppBinding>(R.layout.fragment_about_app) {

    private val viewModel by viewModels<AboutAppViewModel>()

    private val adapter = AboutAppAdapter()

    override fun init() {
        setScreenTitle(getString(com.stathis.core.R.string.about_app_title))

        binding.aboutAppRecycler.apply {
            setupItemDecoration(top = 0, bottom = 0)
            adapter = this@AboutAppFragment.adapter
        }

        viewModel.fetchAboutAppInfo()
    }

    override fun startOps() {
        lifecycleScope.launch {
            viewModel.aboutApp.flowWithLifecycle(lifecycle).collect { data ->
                adapter.submitList(data)
            }
        }
    }

    override fun stopOps() {}
}