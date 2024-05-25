package com.stathis.feature.ui.students

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.stathis.core.MainViewModel
import com.stathis.core.base.BaseFragment
import com.stathis.core.util.setScreenTitle
import com.stathis.core.util.setupItemDecoration
import com.stathis.feature.R
import com.stathis.feature.databinding.FragmentStudentsBinding
import com.stathis.feature.ui.students.adapters.StudentsAdapter
import com.stathis.feature.ui.students.adapters.StudentsCallback
import com.stathis.feature.util.TITLE
import com.stathis.feature.util.URL
import com.stathis.model.general.carousel.CarouselItem
import com.stathis.model.navigation.NavigationAction
import com.stathis.model.network.NetworkResult
import com.stathis.model.students.Link
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
        setScreenTitle(getString(com.stathis.core.R.string.students))

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

    override fun onCarouselTap(model: CarouselItem) = openUrl(
        shouldOpenInBrowser = model.openInBrowser,
        title = model.title,
        url = model.openUrl
    )

    override fun onLinkTap(model: Link) = openUrl(
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