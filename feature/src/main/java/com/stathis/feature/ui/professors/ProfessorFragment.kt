package com.stathis.feature.ui.professors

import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.stathis.core.base.BaseFragment
import com.stathis.core.util.afterTextChanged
import com.stathis.core.util.setScreenTitle
import com.stathis.core.util.setupItemDecoration
import com.stathis.core.util.showProfessorDialog
import com.stathis.feature.R
import com.stathis.feature.databinding.FragmentProfessorsBinding
import com.stathis.feature.ui.professors.adapter.ProfessorsAdapter
import com.stathis.model.professors.Professor
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProfessorFragment : BaseFragment<FragmentProfessorsBinding>(R.layout.fragment_professors) {

    private val viewModel by viewModels<ProfessorViewModel>()

    private val adapter = ProfessorsAdapter { selectedProfessor ->
        openProfessorDialog(selectedProfessor)
    }

    override fun init() {
        setScreenTitle(getString(com.stathis.core.R.string.professors))

        binding.professorsRecycler.apply {
            setupItemDecoration(start = 30, end = 30, bottom = 30)
            adapter = this@ProfessorFragment.adapter
        }
    }

    override fun startOps() {
        viewModel.fetchProfessors()

        binding.searchAction.afterTextChanged { query ->
            viewModel.filterProfessorsByName(query)
        }

        binding.swipeToRefresh.setOnRefreshListener {
            viewModel.fetchProfessors()
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.professors.flowWithLifecycle(lifecycle).collect { list ->
                adapter.submitList(list)
                binding.swipeToRefresh.isRefreshing = false
            }
        }
    }

    override fun stopOps() {}

    private fun openProfessorDialog(professor: Professor) {
        val message = when (professor.gender) {
            resources.getString(com.stathis.core.R.string.male) -> {
                getString(com.stathis.core.R.string.send_email_to_male_professor).format(
                    professor.vocative
                )
            }

            resources.getString(com.stathis.core.R.string.female) -> {
                getString(com.stathis.core.R.string.send_email_to_female_professor).format(
                    professor.vocative
                )
            }

            else -> ""
        }

        showProfessorDialog(message, professor.email)
    }
}