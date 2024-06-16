package com.stathis.personnel.ui.depmemberdetails

import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.stathis.common.base.BaseFragment
import com.stathis.common.util.DEP_MEMBER_INFO
import com.stathis.common.util.getParcelableFromBundle
import com.stathis.common.util.setScreenTitle
import com.stathis.common.util.setupItemDecoration
import com.stathis.common.util.startEmailIntent
import com.stathis.common.util.startNativeBrowserIntent
import com.stathis.model.common.LinkType
import com.stathis.model.department.DepMember
import com.stathis.personnel.R
import com.stathis.personnel.databinding.FragmentDepDetailsBinding
import com.stathis.personnel.ui.depmemberdetails.adapter.DepDetailsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DepDetailsFragment : BaseFragment<FragmentDepDetailsBinding>(R.layout.fragment_dep_details) {

    private val viewModel by viewModels<DepDetailsViewModel>()

    private val adapter = DepDetailsAdapter { model ->
        when (model.type) {
            LinkType.MAIL -> startEmailIntent(model.openUrl)
            else -> startNativeBrowserIntent(model.openUrl)
        }
    }

    override fun init() {
        setScreenTitle(getString(R.string.dep_details_screen_title))

        arguments?.getParcelableFromBundle<DepMember>(DEP_MEMBER_INFO)?.let { model ->
            viewModel.setCurrentDepMember(model)
        }

        binding.detailsRecycler.apply {
            itemAnimator = null
            setupItemDecoration()
            adapter = this@DepDetailsFragment.adapter
        }
    }

    override fun startOps() {
        lifecycleScope.launch {
            viewModel.data.flowWithLifecycle(lifecycle).collect { info ->
                adapter.submitList(info)
            }
        }
    }

    override fun stopOps() {}
}