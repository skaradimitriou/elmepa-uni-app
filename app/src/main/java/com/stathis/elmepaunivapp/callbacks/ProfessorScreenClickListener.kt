package com.stathis.elmepaunivapp.callbacks

import com.stathis.elmepaunivapp.model.professor.Professor

interface ProfessorScreenClickListener {
    fun openDialog(professor : Professor)
}