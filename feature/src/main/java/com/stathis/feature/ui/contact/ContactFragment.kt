package com.stathis.feature.ui.contact

import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.stathis.core.base.BaseFragment
import com.stathis.core.util.setScreenTitle
import com.stathis.core.util.setupItemDecoration
import com.stathis.core.util.startDialIntent
import com.stathis.core.util.startEmailIntent
import com.stathis.feature.R
import com.stathis.feature.databinding.FragmentContactBinding
import com.stathis.feature.ui.contact.adapter.ContactAdapter
import com.stathis.model.contact.ContactType
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
        setScreenTitle(getString(com.stathis.core.R.string.contact))

        viewModel.fetchContactDetails()

        binding.contactsRecycler.apply {
            setupItemDecoration(top = 30, start = 30, end = 30)
            adapter = this@ContactFragment.adapter
        }
    }

    override fun startOps() {
        lifecycleScope.launch {
            viewModel.contactDetails.flowWithLifecycle(lifecycle).collect { data ->
                adapter.submitList(data)
            }
        }
    }

    override fun stopOps() {}
}