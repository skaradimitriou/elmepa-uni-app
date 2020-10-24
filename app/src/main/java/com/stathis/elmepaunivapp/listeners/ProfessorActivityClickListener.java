package com.stathis.elmepaunivapp.listeners;

import com.stathis.elmepaunivapp.ui.professors.model.ProfessorModel;

public interface ProfessorActivityClickListener {

    void sendEmailToProfessor(ProfessorModel professorModel);
    void showDialog(ProfessorModel professorModel);
}
