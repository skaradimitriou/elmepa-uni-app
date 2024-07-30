package com.stathis.support.ui.applicationforms

import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.stathis.common.base.BaseFragment
import com.stathis.common.util.inflateCustomMenu
import com.stathis.common.util.setScreenTitle
import com.stathis.common.util.setupItemDecoration
import com.stathis.common.util.showDialog
import com.stathis.common.util.startNativeBrowserIntent
import com.stathis.model.network.NetworkResult
import com.stathis.support.R
import com.stathis.support.databinding.FragmentApplicationFormsBinding
import com.stathis.support.ui.applicationforms.adapter.ApplicationFormsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ApplicationFormsFragment :
    BaseFragment<FragmentApplicationFormsBinding>(R.layout.fragment_application_forms) {

    private val viewModel by viewModels<ApplicationFormsViewModel>()

    private val adapter = ApplicationFormsAdapter { selectedFormItem ->
        startNativeBrowserIntent(selectedFormItem.openUrl)
    }

    override fun init() {
        setScreenTitle(getString(com.stathis.common.R.string.application_forms_title))

        inflateCustomMenu(
            menuId = R.menu.application_forms_menu,
            respondItemId = R.id.general_info,
            callback = {
                showDialog(
                    title = getString(com.stathis.common.R.string.application_form_info_title),
                    message = getString(com.stathis.common.R.string.application_form_info_body)
                )
            })

        viewModel.fetchApplicationForms()

        binding.applicationFormsRecycler.apply {
            setupItemDecoration()
            adapter = this@ApplicationFormsFragment.adapter
        }
    }

    override fun startOps() {
        lifecycleScope.launch {
            viewModel.applicationForms.flowWithLifecycle(lifecycle).collect { result ->
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