package com.stathis.feature.ui.syllabus

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.stathis.core.base.BaseFragment
import com.stathis.core.util.onTabSelected
import com.stathis.core.util.setScreenTitle
import com.stathis.core.util.setupItemDecoration
import com.stathis.feature.R
import com.stathis.feature.databinding.FragmentSyllabusBinding
import com.stathis.feature.ui.syllabus.adapter.SemesterAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class SyllabusFragment : BaseFragment<FragmentSyllabusBinding>(R.layout.fragment_syllabus) {

    private val viewModel by viewModels<SyllabusViewModel>()

    private val adapter = SemesterAdapter { selectedSemester ->
        Timber.d("Syllabus clicked. $selectedSemester")
        val bundle = Bundle().apply {
            putInt("TAB", binding.tabLayout.selectedTabPosition)
            putString("SEMESTER", selectedSemester.name)
        }
        Timber.d("Bundle created. $bundle")
    }

    override fun init() {
        setScreenTitle(getString(com.stathis.core.R.string.syllabus))

        viewModel.fetchSemesters()

        binding.syllabusRecycler.apply {
            setupItemDecoration(start = 30, end = 30, bottom = 30)
            adapter = this@SyllabusFragment.adapter
        }
    }

    override fun startOps() {
        binding.tabLayout.onTabSelected { tabPosition ->
            viewModel.fetchSemesters()
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.semesters.flowWithLifecycle(lifecycle).collect {
                adapter.submitList(it)
            }
        }
    }

    override fun stopOps() {}
}