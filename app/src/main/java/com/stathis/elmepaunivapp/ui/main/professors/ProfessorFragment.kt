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
        val builder = MaterialAlertDialogBuilder(requireContext())
        builder.setTitle("Νέο e-mail")

        when(professor.gender){
            "male" -> builder.setMessage("Είσαι σίγουρος πως θέλεις να στείλεις e-mail στον κ.${professor.vocative};")
            "female" -> builder.setMessage("Είσαι σίγουρος πως θέλεις να στείλεις e-mail στην κ.${professor.vocative};")
        }

        builder.setPositiveButton(
            "Ναι"
        ) { dialog, which -> sendEmail(professor) }
        builder.setNegativeButton(
            "Άκυρο"
        ) { dialog, which -> dialog.dismiss() }
        builder.show()
    }

    private fun sendEmail(professor : Professor){
        val i = Intent(Intent.ACTION_SEND)
            .setType("message/rfc822")
            .putExtra(Intent.EXTRA_EMAIL, arrayOf<String>(professor.email))

        try {
            startActivity(Intent.createChooser(i, "Send mail..."))
        } catch (ex: ActivityNotFoundException) {
            Toast.makeText(requireContext(),"There are no email clients installed.",Toast.LENGTH_SHORT).show()
        }
    }

    override fun stopOps() = viewModel.removeObserver(this)
}