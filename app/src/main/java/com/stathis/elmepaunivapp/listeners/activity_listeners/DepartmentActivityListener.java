package com.stathis.elmepaunivapp.listeners.activity_listeners;

import com.stathis.elmepaunivapp.ui.department.model.DepMembers;
import com.stathis.elmepaunivapp.ui.department.model.DeptFieldsOfStudy;
import com.stathis.elmepaunivapp.ui.department.model.Programmes;
import com.stathis.elmepaunivapp.ui.department.model.Research;
import com.stathis.elmepaunivapp.ui.department.model.SocialChannels;
import com.stathis.elmepaunivapp.ui.department.model.VirtualTour;

public interface DepartmentActivityListener {

    void goToSyllabus(DeptFieldsOfStudy data);
    void openProgrammes(Programmes data);
    void openDepMembers(DepMembers data);
    void openSocial(SocialChannels data);
    void goToVirtualTour(VirtualTour data);
    void goToResearch(Research data);
}
