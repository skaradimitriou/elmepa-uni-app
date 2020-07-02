package com.stathis.elmepaunivapp.models;

public class Lesson {

    private String name;
    private String mandatory;
    private String description;

    public Lesson(String name, String mandatory, String description) {
        this.name = name;
        this.mandatory = mandatory;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMandatory() {
        return mandatory;
    }

    public void setMandatory(String mandatory) {
        this.mandatory = mandatory;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "name='" + name + '\'' +
                ", mandatory='" + mandatory + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
