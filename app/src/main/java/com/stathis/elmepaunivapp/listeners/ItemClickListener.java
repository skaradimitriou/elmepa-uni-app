package com.stathis.elmepaunivapp.listeners;


import android.view.View;

import com.stathis.elmepaunivapp.models.DeptFieldsOfStudy;
import com.stathis.elmepaunivapp.ui.professors.model.ProfessorModel;
import com.stathis.elmepaunivapp.models.Programmes;
import com.stathis.elmepaunivapp.models.SocialChannels;
import com.stathis.elmepaunivapp.ui.students.model.UsefulLinks;

public interface ItemClickListener extends View.OnClickListener {

    void onItemClick(DeptFieldsOfStudy item);
    void onProgrammesClick(Programmes programmes);
    void onProfessorClick(ProfessorModel professorModel);
    void onUsefulLinksClick(UsefulLinks usefulLinks);
    void onSocialItemClick(SocialChannels socialChannels);
}
