package com.stathis.elmepaunivapp.listeners;

import com.stathis.elmepaunivapp.ui.department.model.Research;
import com.stathis.elmepaunivapp.ui.department.model.VirtualTour;

public interface DepartmentCardClickListener {

    void onVirtualTourClick(VirtualTour data);
    void onResearchClick(Research data);
}
