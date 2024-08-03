package com.stathis.students.main

import android.os.Bundle
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.stathis.common.MainViewModel
import com.stathis.common.base.BaseFragment
import com.stathis.common.util.TITLE
import com.stathis.common.util.URL
import com.stathis.common.util.setScreenTitle
import com.stathis.common.util.setupItemDecoration
import com.stathis.common.util.startNativeBrowserIntent
import com.stathis.model.general.carousel.CarouselItem
import com.stathis.model.navigation.NavigationAction
import com.stathis.model.network.NetworkResult
import com.stathis.model.students.StudentLink
import com.stathis.students.R
import com.stathis.students.databinding.FragmentStudentsBinding
import com.stathis.students.main.adapters.StudentsAdapter
import com.stathis.students.main.adapters.StudentsCallback
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class StudentsFragment : BaseFragment<FragmentStudentsBinding>(R.layout.fragment_students),
    StudentsCallback {

    private val viewModel by viewModels<StudentsViewModel>()
    private val activityVM by activityViewModels<MainViewModel>()

    private val adapter = StudentsAdapter(this)

    override fun init() {
        setScreenTitle(getString(com.stathis.common.R.string.students))

        viewModel.fetchStudentInformation()

        binding.studentsRec.apply {
            itemAnimator = null
            setupItemDecoration()
            adapter = this@StudentsFragment.adapter
        }
    }

    override fun startOps() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.data.collect { result ->
                when (result) {
                    is NetworkResult.Loading -> {
                        adapter.submitList(result.data)
                    }

                    is NetworkResult.Success -> {
                        adapter.submitList(result.data)
                    }

                    is NetworkResult.Failure -> {
                        Timber.d("${result.errorBody}")
                    }
                }
            }
        }
    }

    override fun stopOps() {}

    override fun onCarouselTap(model: CarouselItem) = when (model.action) {
        NavigationAction.ACADEMIC_SCHEDULE -> activityVM.navigateWithAction(model.action)
        else -> {
            openUrl(
                shouldOpenInBrowser = model.openInBrowser,
                title = model.title,
                url = model.openUrl
            )
        }
    }

    override fun onLinkTap(model: StudentLink) = openUrl(
        shouldOpenInBrowser = model.openInBrowser,
        title = model.title,
        url = model.openUrl
    )

    private fun openUrl(shouldOpenInBrowser: Boolean, title: String? = null, url: String) {
        if (shouldOpenInBrowser) {
            startNativeBrowserIntent(url = url)
        } else {
            openWebView(title, url)
        }
    }

    private fun openWebView(title: String? = null, url: String) {
        val args = Bundle().apply {
            putString(TITLE, title)
            putString(URL, url)
        }
        activityVM.navigateWithAction(NavigationAction.WEBVIEW, args)
    }
}