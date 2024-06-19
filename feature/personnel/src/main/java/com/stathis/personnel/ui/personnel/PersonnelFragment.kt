package com.stathis.personnel.ui.personnel

import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.stathis.common.base.BaseFragment
import com.stathis.common.bottomsheet.BottomSheetOption
import com.stathis.common.bottomsheet.OptionAction
import com.stathis.common.bottomsheet.OptionsBottomSheet
import com.stathis.common.util.inflateCustomMenu
import com.stathis.common.util.respondToQuery
import com.stathis.common.util.setScreenTitle
import com.stathis.common.util.setupItemDecoration
import com.stathis.common.util.startEmailIntent
import com.stathis.common.util.startShareIntent
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
        showAvailableOptions(selectedPersonnel)
    }

    private fun showAvailableOptions(selectedPersonnel: Person) {
        val options = listOf(
            BottomSheetOption(
                getString(R.string.share_option),
                showSeparator = true,
                type = OptionAction.SHARE
            ),
            BottomSheetOption(getString(R.string.email_option), type = OptionAction.SEND_EMAIL)
        )

        OptionsBottomSheet.Builder()
            .setOptions(options)
            .setListener { model ->
                when (model.type) {
                    OptionAction.SHARE -> {
                        startShareIntent(
                            subject = getString(R.string.share_personnel_data),
                            body = getString(
                                R.string.share_personnel_data_format,
                                selectedPersonnel.fullName,
                                selectedPersonnel.email
                            )
                        )
                    }

                    OptionAction.SEND_EMAIL -> startEmailIntent(selectedPersonnel.email)
                }
            }
            .build()
            .show(parentFragmentManager, OptionsBottomSheet.GENERIC_BS_TAG)
    }

    override fun init() {
        setScreenTitle(getString(com.stathis.common.R.string.personnel))

        inflateCustomMenu(
            menuId = R.menu.personnel_menu,
            respondItemId = R.id.action_search,
            callback = { menuItem ->
                menuItem.respondToQuery(
                    queryHint = getString(com.stathis.common.R.string.search_in_personnel)
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

    override fun stopOps() = Unit
}