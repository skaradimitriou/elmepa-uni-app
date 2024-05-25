package com.stathis.personnel.ui.personnel

import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.stathis.core.base.BaseFragment
import com.stathis.core.util.inflateCustomMenu
import com.stathis.core.util.respondToQuery
import com.stathis.core.util.setScreenTitle
import com.stathis.core.util.setupItemDecoration
import com.stathis.core.util.showPersonnelDialog
import com.stathis.model.network.NetworkResult
import com.stathis.model.personnel.Person
import com.stathis.personnel.R
import com.stathis.personnel.databinding.FragmentPersonnelBinding
import com.stathis.personnel.ui.personnel.adapter.PersonnelAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PersonnelFragment : BaseFragment<FragmentPersonnelBinding>(R.layout.fragment_personnel) {

    private val viewModel by viewModels<PersonnelViewModel>()

    private val adapter = PersonnelAdapter { selectedPersonnel ->
        openDialog(selectedPersonnel)
    }

    override fun init() {
        setScreenTitle(getString(com.stathis.core.R.string.personnel))

        inflateCustomMenu(
            menuId = R.menu.personnel_menu,
            respondItemId = R.id.action_search,
            callback = { menuItem ->
                menuItem.respondToQuery(
                    queryHint = getString(com.stathis.core.R.string.search_in_personnel)
                ) { query -> viewModel.filterPersonnelByName(query) }
            })

        binding.personnelRecycler.apply {
            setupItemDecoration()
            adapter = this@PersonnelFragment.adapter
        }

        viewModel.fetchPersonnel()
    }

    override fun startOps() {
        binding.swipeToRefresh.setOnRefreshListener {
            viewModel.fetchPersonnel()
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.personnel.flowWithLifecycle(lifecycle).collect { state ->
                when (state) {
                    is NetworkResult.Loading -> {
                        adapter.submitList(state.data)
                    }

                    is NetworkResult.Success -> {
                        adapter.submitList(state.data)
                        binding.showEmptyResults = state.data?.isEmpty()
                        binding.swipeToRefresh.isRefreshing = false
                    }

                    is NetworkResult.Failure -> Unit
                }
            }
        }
    }

    override fun stopOps() {}

    private fun openDialog(person: Person) {
        val message = when (person.gender) {
            resources.getString(com.stathis.core.R.string.male) -> {
                getString(com.stathis.core.R.string.send_email_to_male_personnel).format(
                    person.vocative
                )
            }

            resources.getString(com.stathis.core.R.string.female) -> {
                getString(com.stathis.core.R.string.send_email_to_female_personnel).format(
                    person.vocative
                )
            }

            else -> ""
        }

        showPersonnelDialog(message, person.email)
    }
}