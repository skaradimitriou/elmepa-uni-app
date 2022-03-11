package com.stathis.elmepaunivapp.callbacks

import com.stathis.elmepaunivapp.ui.home.fragments.professors.model.Professor

interface ProfessorScreenClickListener {
    fun openDialog(professor : Professor)
}