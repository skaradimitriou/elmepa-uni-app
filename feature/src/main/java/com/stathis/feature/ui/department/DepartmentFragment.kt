package com.stathis.feature.ui.department

import android.content.Intent
import android.net.Uri
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
import com.stathis.feature.databinding.FragmentDepartmentBinding
import com.stathis.feature.ui.department.adapter.DepartmentAdapter
import com.stathis.feature.ui.department.adapter.DepartmentCallback
import com.stathis.feature.util.TITLE
import com.stathis.feature.util.URL
import com.stathis.model.department.Programme
import com.stathis.model.department.SocialItem
import com.stathis.model.general.carousel.CarouselItem
import com.stathis.model.navigation.NavigationAction
import com.stathis.model.network.NetworkResult
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DepartmentFragment : BaseFragment<FragmentDepartmentBinding>(R.layout.fragment_department),
    DepartmentCallback {

    private val viewModel by viewModels<DepartmentViewModel>()
    private val activityVM by activityViewModels<MainViewModel>()

    private val adapter = DepartmentAdapter(this)

    override fun init() {
        setScreenTitle(getString(com.stathis.core.R.string.department))

        binding.departmentRecycler.apply {
            itemAnimator = null
            setupItemDecoration()
            adapter = this@DepartmentFragment.adapter
        }

        viewModel.fetchScreenDetails()
    }

    override fun startOps() {
        lifecycleScope.launch {
            viewModel.data.flowWithLifecycle(lifecycle).collect { result ->
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

    override fun onCarouselItemTap(model: CarouselItem) = openUrl(
        shouldOpenInBrowser = model.openInBrowser,
        title = model.title,
        url = model.openUrl
    )

    override fun onProgrammeItemTap(model: Programme) = openUrl(
        shouldOpenInBrowser = model.openInBrowser,
        title = model.title,
        url = model.openUrl
    )

    override fun onSocialItemTap(model: SocialItem) = openUrl(
        shouldOpenInBrowser = model.openInBrowser,
        title = model.title,
        url = model.openUrl
    )

    private fun openUrl(shouldOpenInBrowser: Boolean, title: String? = null, url: String) {
        if (shouldOpenInBrowser) {
            openNativeBrowser(url)
        } else {
            openWebView(title, url)
        }
    }

    private fun openNativeBrowser(url: String) {
        startActivity(Intent(Intent.ACTION_VIEW).apply { data = Uri.parse(url) })
    }

    private fun openWebView(title: String? = null, url: String) {
        val args = Bundle().apply {
            putString(TITLE, title)
            putString(URL, url)
        }
        activityVM.navigateWithAction(NavigationAction.WEBVIEW, args)
    }
}