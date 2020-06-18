package com.stathis.elmepaunivapp.models;

public class DeptFieldsOfStudy {

    String name;

    public DeptFieldsOfStudy(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "DeptFieldsOfStudy{" +
                "name='" + name + '\'' +
                '}';
    }

}
