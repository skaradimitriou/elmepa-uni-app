package com.stathis.syllabus.ui.syllabus

import android.os.Bundle
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.stathis.common.MainSharedViewModel
import com.stathis.common.MainViewModel
import com.stathis.common.base.BaseFragment
import com.stathis.common.util.onTabSelected
import com.stathis.common.util.setScreenTitle
import com.stathis.common.util.setupItemDecoration
import com.stathis.model.navigation.NavigationAction
import com.stathis.model.network.NetworkResult
import com.stathis.syllabus.R
import com.stathis.syllabus.databinding.FragmentSyllabusBinding
import com.stathis.syllabus.ui.syllabus.adapter.OrientationAdapter
import com.stathis.syllabus.util.ORIENTATION
import com.stathis.syllabus.util.PROGRAMME
import com.stathis.syllabus.util.SEMESTER
import com.stathis.syllabus.util.toProgrammeType
import com.stathis.syllabus.util.toTabPosition
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SyllabusFragment : BaseFragment<FragmentSyllabusBinding>(R.layout.fragment_syllabus) {

    private val viewModel by viewModels<SyllabusViewModel>()
    private val activityVM by activityViewModels<MainViewModel>()
    private val sharedVM by activityViewModels<MainSharedViewModel>()

    private val adapter = OrientationAdapter { programmeType, orientationType, semester ->
        val args = Bundle().apply {
            putSerializable(PROGRAMME, programmeType)
            putSerializable(ORIENTATION, orientationType)
            putString(SEMESTER, semester)
        }

        sharedVM.selectedProgrammeType = programmeType
        sharedVM.selectedOrientation = orientationType

        activityVM.navigateWithAction(NavigationAction.LESSONS, args)
    }

    override fun init() {
        setScreenTitle(getString(com.stathis.common.R.string.syllabus))

        val programme = sharedVM.selectedProgrammeType
        binding.syllabusTabLayout.getTabAt(programme.toTabPosition())?.select()

        viewModel.fetchSemestersByProgramme(
            programme = programme,
            orientation = sharedVM.selectedOrientation
        )

        binding.syllabusRecycler.apply {
            setupItemDecoration()
            adapter = this@SyllabusFragment.adapter
        }

        binding.syllabusTabLayout.onTabSelected { tab ->
            val type = tab.position.toProgrammeType()
            viewModel.fetchSemestersByProgramme(programme = type)
        }
    }

    override fun startOps() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.semesters.flowWithLifecycle(lifecycle).collect { result ->
                when (result) {
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