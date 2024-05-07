package com.stathis.feature.ui.department

import android.os.Bundle
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.stathis.core.base.BaseFragment
import com.stathis.core.util.setScreenTitle
import com.stathis.core.util.setupItemDecoration
import com.stathis.feature.R
import com.stathis.feature.databinding.FragmentDepartmentBinding
import com.stathis.feature.navigation.NavigationAction
import com.stathis.feature.ui.MainViewModel
import com.stathis.feature.ui.department.adapter.DepartmentAdapter
import com.stathis.feature.ui.department.adapter.DepartmentCallback
import com.stathis.feature.util.TITLE
import com.stathis.feature.util.URL
import com.stathis.model.department.Programme
import com.stathis.model.department.SocialItem
import com.stathis.model.general.carousel.CarouselItem
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
            setupItemDecoration(top = 30, start = 30, end = 30)
            adapter = this@DepartmentFragment.adapter
        }

        viewModel.fetchScreenDetails()
    }

    override fun startOps() {
        lifecycleScope.launch {
            viewModel.data.flowWithLifecycle(lifecycle).collect { list ->
                adapter.submitList(list)
            }
        }
    }

    override fun stopOps() {}

    override fun onCarouselItemTap(model: CarouselItem) = openWebView(model.title, model.openUrl)

    override fun onProgrammeItemTap(model: Programme) = openWebView(model.title, model.openUrl)

    override fun onSocialItemTap(model: SocialItem) = openWebView(model.title, model.openUrl)

    private fun openWebView(title: String, url: String) {
        val args = Bundle().apply {
            putString(TITLE, title)
            putString(URL, url)
        }
        activityVM.navigateWithAction(NavigationAction.WEBVIEW, args)
    }
}