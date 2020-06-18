package com.stathis.elmepaunivapp.models;

public class Programmes {

    String name;
    String description;

    public Programmes(String name, String description) {
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
        return "Programmes{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
