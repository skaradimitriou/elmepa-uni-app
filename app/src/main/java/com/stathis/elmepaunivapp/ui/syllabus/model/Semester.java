package com.stathis.elmepaunivapp.ui.syllabus.model;

import com.stathis.elmepaunivapp.ui.syllabus_lessons.model.Lesson;

import java.util.List;

public class Semester extends Object {

    String semester;
    List<Lesson> lessons;

    public Semester(String semester, List<Lesson> lessons) {
        this.semester = semester;
        this.lessons = lessons;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }
}
