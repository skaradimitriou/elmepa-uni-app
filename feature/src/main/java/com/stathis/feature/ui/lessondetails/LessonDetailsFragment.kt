package com.stathis.feature.ui.lessondetails

import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.stathis.core.base.BaseFragment
import com.stathis.core.util.setScreenTitle
import com.stathis.feature.R
import com.stathis.feature.databinding.FragmentLessonDetailsBinding
import com.stathis.feature.ui.lessondetails.adapter.LessonDetailsAdapter
import com.stathis.feature.util.LESSON
import com.stathis.model.network.NetworkResult
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LessonDetailsFragment :
    BaseFragment<FragmentLessonDetailsBinding>(R.layout.fragment_lesson_details) {

    private val viewModel by viewModels<LessonDetailsViewModel>()

    private val adapter = LessonDetailsAdapter()

    override fun init() {
        setScreenTitle(getString(com.stathis.core.R.string.lesson_information))

        val lessonName = arguments?.getString(LESSON) ?: ""
        viewModel.fetchLessonDetails(lessonName)

        binding.lessonDetailsRecycler.adapter = adapter
    }

    override fun startOps() {
        lifecycleScope.launch {
            viewModel.lessonDetails.flowWithLifecycle(lifecycle).collect { result ->
                when (result) {
                    is NetworkResult.Loading -> Unit
                    is NetworkResult.Success -> {
                        adapter.submitList(result.data)
                    }

                    is NetworkResult.Failure -> Unit
                }
            }
        }
    }

    override fun stopOps() {}
}