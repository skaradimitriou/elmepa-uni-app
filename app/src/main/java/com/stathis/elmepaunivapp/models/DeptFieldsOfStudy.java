package com.stathis.elmepaunivapp.models;

public class DeptFieldsOfStudy {

    String name;
    String description;

    public DeptFieldsOfStudy(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "FieldsOfStudy{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

}
