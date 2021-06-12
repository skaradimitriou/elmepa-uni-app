package com.stathis.elmepaunivapp.ui.syllabus.model;

import java.util.List;

public class Semester extends Object {

    String semester;
    String lessonInfo;
    List<Lesson> lessons;

    public Semester(String semester, String lessonInfo, List<Lesson> lessons) {
        this.semester = semester;
        this.lessonInfo = lessonInfo;
        this.lessons = lessons;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getLessonInfo() {
        return lessonInfo;
    }

    public void setLessonInfo(String lessonInfo) {
        this.lessonInfo = lessonInfo;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }
}
