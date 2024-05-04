package com.stathis.feature.ui.students

import android.os.Bundle
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.stathis.core.base.BaseFragment
import com.stathis.core.util.setScreenTitle
import com.stathis.core.util.setupItemDecoration
import com.stathis.feature.R
import com.stathis.feature.databinding.FragmentStudentsBinding
import com.stathis.feature.navigation.NavigationAction
import com.stathis.feature.ui.MainViewModel
import com.stathis.feature.ui.students.adapters.StudentsAdapter
import com.stathis.feature.ui.students.adapters.StudentsCallback
import com.stathis.feature.util.TITLE
import com.stathis.feature.util.URL
import com.stathis.model.general.carousel.CarouselItem
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
            setupItemDecoration(top = 30, start = 30, end = 30)
            adapter = this@StudentsFragment.adapter
        }
    }

    override fun startOps() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.data.collect { result ->
                when (result) {
                    is NetworkResult.Loading -> {
                        Timber.d("${result}")
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

    override fun onCarouselTap(model: CarouselItem) = openWebView(model.title, model.openUrl)

    override fun onLinkTap(model: Link) = openWebView(model.title, model.openUrl)

    private fun openWebView(title: String, url: String) {
        val args = Bundle().apply {
            putString(TITLE, title)
            putString(URL, url)
        }
        activityVM.navigateWithAction(NavigationAction.WEBVIEW, args)
    }


//
//    override fun startOps() {
//        viewModel.observe(this)
//        viewModel.bindCallbacks(object : StudentsScreenCallback {
//            override fun openCarouselItem(item: CarouselItem) = openUrl(item.url, item.webTitle)
//            override fun openLink(item: LinkItem) = when (item.title) {
//                SECRETARY, STUDENTS_PLATFORM, STUD_FB_PG, EDU_MAIL -> openBrowser(item.url)
//                else -> openUrl(item.url, item.title)
//            }
//        })
//    }
//
//    override fun stopOps() = viewModel.release(this)
//
//    private fun openBrowser(url: String) = startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
//
//    private fun openUrl(url: String, title: String? = null) {
//        startActivity(Intent(this, WebViewFragment::class.java).apply {
//            putExtra(URL, url)
//            putExtra(TITLE, title)
//        })
//    }
//
//    override fun onSupportNavigateUp(): Boolean {
//        onBackPressed()
//        return true
//    }
}