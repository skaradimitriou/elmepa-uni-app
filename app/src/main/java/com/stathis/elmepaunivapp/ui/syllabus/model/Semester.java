package com.stathis.elmepaunivapp.ui.syllabus.model;

import com.stathis.elmepaunivapp.models.Lesson;

import java.util.ArrayList;

public class Semester extends Object {

    String semester;

    public Semester(String semester) {
        this.semester = semester;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }
}