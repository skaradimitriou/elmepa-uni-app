package com.stathis.elmepaunivapp.listeners.activity_listeners;

import com.stathis.elmepaunivapp.ui.students.model.Schedule;
import com.stathis.elmepaunivapp.ui.students.model.UsefulLinks;

public interface StudentsActivityListener {

    void openStudentsSchedule(Schedule schedule);
    void itemClicked(UsefulLinks usefulLinks);
    void goToSyllabus(UsefulLinks usefulLinks);
}
