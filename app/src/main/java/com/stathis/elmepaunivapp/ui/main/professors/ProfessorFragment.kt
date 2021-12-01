package com.stathis.elmepaunivapp.ui.main.professors

import android.content.ActivityNotFoundException
import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.stathis.elmepaunivapp.R
import com.stathis.elmepaunivapp.abstraction.ElmepaFragment
import com.stathis.elmepaunivapp.callbacks.ProfessorScreenClickListener
import com.stathis.elmepaunivapp.ui.main.professors.model.Professor
import kotlinx.android.synthetic.main.fragment_professors.*

class ProfessorFragment : ElmepaFragment(R.layout.fragment_professors) {

    private lateinit var viewModel : ProfessorViewModel

    override fun init(view : View) {
        viewModel = ViewModelProvider(this).get(ProfessorViewModel::class.java)
    }

    override fun startOps() {
        recyclerView.adapter = viewModel.adapter

        search_action.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                viewModel.filter(p0.toString())
            }
        })

        viewModel.getProfessors(object : ProfessorScreenClickListener {
            override fun openDialog(professor: Professor) {
                openPopUpWindow(professor)
            }
        })

        viewModel.observeData(this)
    }

    private fun openPopUpWindow(professor : Professor) {
        MaterialAlertDialogBuilder(requireContext()).also {
            it.setTitle(getString(R.string.dialog_new_email))
            when(professor.gender){
                resources.getString(R.string.male) -> it.setMessage(getString(R.string.send_email_to_male_professor).format(professor.vocative))
                resources.getString(R.string.female) -> it.setMessage(getString(R.string.send_email_to_female_professor).format(professor.vocative))
            }

            it.setPositiveButton(getString(R.string.dialog_yes)) { dialog, which -> sendEmail(professor) }
            it.setNegativeButton(getString(R.string.dialog_cancel)) { dialog, which -> dialog.dismiss() }
        }.show()
    }

    private fun sendEmail(professor : Professor){
        val i = Intent(Intent.ACTION_SEND)
            .setType(getString(R.string.email_type))
            .putExtra(Intent.EXTRA_EMAIL, arrayOf(professor.email))

        try {
            startActivity(Intent.createChooser(i, getString(R.string.sending_email)))
        } catch (ex: ActivityNotFoundException) {
            Toast.makeText(requireContext(),getString(R.string.no_clients_installed),Toast.LENGTH_SHORT).show()
        }
    }

    override fun stopOps() = viewModel.removeObserver(this)
}