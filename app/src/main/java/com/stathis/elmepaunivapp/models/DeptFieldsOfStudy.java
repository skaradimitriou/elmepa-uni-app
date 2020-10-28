package com.stathis.elmepaunivapp.models;

public class DeptFieldsOfStudy extends Object {

    private String name;
    private String direction;
    private int ImageResource;

    public DeptFieldsOfStudy(String name, String direction, int imageResource) {
        this.name = name;
        this.direction = direction;
        ImageResource = imageResource;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int getImageResource() {
        return ImageResource;
    }

    public void setImageResource(int imageResource) {
        ImageResource = imageResource;
    }

    @Override
    public String toString() {
        return "DeptFieldsOfStudy{" +
                "name='" + name + '\'' +
                ", direction='" + direction + '\'' +
                ", ImageResource=" + ImageResource +
                '}';
    }
}
