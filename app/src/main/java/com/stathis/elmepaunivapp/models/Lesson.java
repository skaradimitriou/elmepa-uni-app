package com.stathis.elmepaunivapp.models;

public class Lesson {

    private String name;
    private String mandatory;
    private String description;
    private String direction;

    public Lesson(String name, String mandatory, String description, String direction) {
        this.name = name;
        this.mandatory = mandatory;
        this.description = description;
        this.direction = direction;
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

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}
