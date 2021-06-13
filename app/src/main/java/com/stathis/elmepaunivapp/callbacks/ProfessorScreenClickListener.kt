package com.stathis.elmepaunivapp.callbacks

import com.stathis.elmepaunivapp.ui.professors.model.Professor

interface ProfessorScreenClickListener {

    fun openDialog(professor : Professor)
}