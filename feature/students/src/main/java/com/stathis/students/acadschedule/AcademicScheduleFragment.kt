package com.stathis.students.acadschedule

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.stathis.common.base.BaseFragment
import com.stathis.common.util.setScreenTitle
import com.stathis.common.util.setupItemDecoration
import com.stathis.model.network.NetworkResult
import com.stathis.students.R
import com.stathis.students.acadschedule.adapter.AcademicScheduleAdapter
import com.stathis.students.databinding.FragmentAcademicScheduleBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class AcademicScheduleFragment :
    BaseFragment<FragmentAcademicScheduleBinding>(R.layout.fragment_academic_schedule) {

    private val viewModel by viewModels<AcademicScheduleViewModel>()
    private val adapter = AcademicScheduleAdapter()

    override fun init() {
        setScreenTitle(getString(com.stathis.common.R.string.acad_schedule_title))

        binding.academicScheduleRecycler.apply {
            setupItemDecoration()
            adapter = this@AcademicScheduleFragment.adapter
        }

        viewModel.fetchAcademicSchedule()
    }

    override fun startOps() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.schedule.collect { result ->
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

    override fun stopOps() = Unit
}