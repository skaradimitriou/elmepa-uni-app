package com.stathis.support.ui.contact

import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.stathis.common.base.BaseFragment
import com.stathis.common.util.setScreenTitle
import com.stathis.common.util.setupItemDecoration
import com.stathis.common.util.startDialIntent
import com.stathis.common.util.startEmailIntent
import com.stathis.model.contact.ContactType
import com.stathis.model.network.NetworkResult
import com.stathis.support.R
import com.stathis.support.databinding.FragmentContactBinding
import com.stathis.support.ui.contact.adapter.ContactAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ContactFragment : BaseFragment<FragmentContactBinding>(R.layout.fragment_contact) {

    private val viewModel by viewModels<ContactViewModel>()

    val adapter = ContactAdapter { selectedItem ->
        when (selectedItem.contactType) {
            ContactType.EMAIL -> startEmailIntent(emailAddress = selectedItem.email)
            ContactType.TELEPHONE -> startDialIntent(numberToDial = selectedItem.telephone)
            else -> Unit
        }
    }

    override fun init() {
        setScreenTitle(getString(com.stathis.common.R.string.contact))

        viewModel.fetchContactDetails()

        binding.contactsRecycler.apply {
            setupItemDecoration()
            adapter = this@ContactFragment.adapter
        }
    }

    override fun startOps() {
        lifecycleScope.launch {
            viewModel.contactDetails.flowWithLifecycle(lifecycle).collect { result ->
                when (result) {
                    is NetworkResult.Loading -> {
                        adapter.submitList(result.data)
                    }

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