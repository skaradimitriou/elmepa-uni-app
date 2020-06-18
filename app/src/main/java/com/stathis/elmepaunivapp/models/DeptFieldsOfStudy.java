package com.stathis.elmepaunivapp.models;

public class DeptFieldsOfStudy {

    private String name;
    private int ImageResource;

    public DeptFieldsOfStudy(String name, int imageResource) {
        this.name = name;
        ImageResource = imageResource;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
                ", ImageResource=" + ImageResource +
                '}';
    }
}
