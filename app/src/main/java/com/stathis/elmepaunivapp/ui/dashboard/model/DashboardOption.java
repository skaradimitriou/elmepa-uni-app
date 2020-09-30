package com.stathis.elmepaunivapp.ui.dashboard.model;

public class DashboardOption extends Object {

    private String title;
    private Integer drawable;

    public DashboardOption(String title, Integer drawable) {
        this.title = title;
        this.drawable = drawable;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getDrawable() {
        return drawable;
    }

    public void setDrawable(Integer drawable) {
        this.drawable = drawable;
    }

    @Override
    public String toString() {
        return "DashboardOption{" +
                "title='" + title + '\'' +
                ", drawable=" + drawable +
                '}';
    }
}
