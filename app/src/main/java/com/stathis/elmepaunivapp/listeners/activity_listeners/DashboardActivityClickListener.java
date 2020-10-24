package com.stathis.elmepaunivapp.listeners.activity_listeners;

import com.stathis.elmepaunivapp.ui.dashboard.model.DashboardOption;

public interface DashboardActivityClickListener {

    void goToAnnouncementScreen(DashboardOption dashboardOption);
    void goToDepartmentScreen(DashboardOption dashboardOption);
    void goToProfessorScreen(DashboardOption dashboardOption);
    void goToStudentsScreen(DashboardOption dashboardOption);
}
