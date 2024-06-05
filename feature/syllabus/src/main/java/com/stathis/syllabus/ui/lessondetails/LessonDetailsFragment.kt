package com.stathis.syllabus.ui.lessondetails

import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.stathis.common.MainSharedViewModel
import com.stathis.common.base.BaseFragment
import com.stathis.common.util.setScreenTitle
import com.stathis.common.util.toNotNull
import com.stathis.model.network.NetworkResult
import com.stathis.syllabus.R
import com.stathis.syllabus.databinding.FragmentLessonDetailsBinding
import com.stathis.syllabus.ui.lessondetails.adapter.LessonDetailsAdapter
import com.stathis.syllabus.util.LESSON
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LessonDetailsFragment :
    BaseFragment<FragmentLessonDetailsBinding>(R.layout.fragment_lesson_details) {

    private val viewModel by viewModels<LessonDetailsViewModel>()
    private val sharedVM by activityViewModels<MainSharedViewModel>()

    private val adapter = LessonDetailsAdapter()

    override fun init() {
        setScreenTitle(getString(com.stathis.common.R.string.lesson_information))

        val lessonName = arguments?.getString(LESSON).toNotNull()
        val programme = sharedVM.selectedProgrammeType

        viewModel.fetchLessonDetails(
            programmeType = programme,
            lessonName = lessonName
        )

        binding.lessonDetailsRecycler.adapter = adapter
    }

    override fun startOps() {
        lifecycleScope.launch {
            viewModel.lessonDetails.flowWithLifecycle(lifecycle).collect { result ->
                when (result) {
                    is NetworkResult.Loading -> binding.isLoading = true
                    is NetworkResult.Success -> {
                        binding.isLoading = false
                        adapter.submitList(result.data)
                    }

                    is NetworkResult.Failure -> binding.isLoading = false
                }
            }
        }
    }

    override fun stopOps() {}
}