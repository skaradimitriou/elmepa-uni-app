package com.stathis.elmepaunivapp.callbacks

import com.stathis.elmepaunivapp.ui.home.professors.model.Professor

interface ProfessorScreenClickListener {
    fun openDialog(professor : Professor)
}