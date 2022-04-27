package com.stathis.elmepaunivapp.ui.home.professors

import android.content.ActivityNotFoundException
import android.content.Intent
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.stathis.elmepaunivapp.R
import com.stathis.elmepaunivapp.abstraction.ElmepaFragment
import com.stathis.elmepaunivapp.callbacks.ProfessorScreenClickListener
import com.stathis.elmepaunivapp.databinding.FragmentProfessorsBinding
import com.stathis.elmepaunivapp.ui.home.professors.model.Professor
import com.stathis.elmepaunivapp.util.afterTextChanged
import com.stathis.elmepaunivapp.util.stopRefresh

class ProfessorFragment : ElmepaFragment<FragmentProfessorsBinding>(R.layout.fragment_professors) {

    private val viewModel: ProfessorViewModel by viewModels()

    override fun init() {
        binding.viewModel = viewModel
        activity?.title = getString(R.string.dashboard_professors)
    }

    override fun startOps() {
        binding.searchAction.afterTextChanged { query ->
            viewModel.filter(query)
        }

        binding.swipeToRefresh.setOnRefreshListener {
            viewModel.getData()
        }

        viewModel.addCallback(object : ProfessorScreenClickListener {
            override fun openDialog(professor: Professor) = openPopUpWindow(professor)
        })

        viewModel.professors.observe(viewLifecycleOwner) {
            binding.swipeToRefresh.stopRefresh()
        }

        viewModel.observe(viewLifecycleOwner)
    }

    private fun openPopUpWindow(professor: Professor) {
        MaterialAlertDialogBuilder(requireContext()).also {
            it.setTitle(getString(R.string.dialog_new_email))
            when (professor.gender) {
                resources.getString(R.string.male) -> it.setMessage(
                    getString(R.string.send_email_to_male_professor).format(
                        professor.vocative
                    )
                )
                resources.getString(R.string.female) -> it.setMessage(
                    getString(R.string.send_email_to_female_professor).format(
                        professor.vocative
                    )
                )
            }

            it.setPositiveButton(getString(R.string.dialog_yes)) { dialog, which ->
                sendEmail(
                    professor
                )
            }
            it.setNegativeButton(getString(R.string.dialog_cancel)) { dialog, which -> dialog.dismiss() }
        }.show()
    }

    private fun sendEmail(professor: Professor) {
        val i = Intent(Intent.ACTION_SEND)
            .setType(getString(R.string.email_type))
            .putExtra(Intent.EXTRA_EMAIL, arrayOf(professor.email))

        try {
            startActivity(Intent.createChooser(i, getString(R.string.sending_email)))
        } catch (ex: ActivityNotFoundException) {
            Toast.makeText(
                requireContext(),
                getString(R.string.no_clients_installed),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun stopOps() = viewModel.release(viewLifecycleOwner)
}