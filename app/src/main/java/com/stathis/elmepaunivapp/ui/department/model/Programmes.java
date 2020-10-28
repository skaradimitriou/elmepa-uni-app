package com.stathis.elmepaunivapp.ui.department.model;

public class Programmes extends Object {

    private String name;
    private String description;
    private int ImageResource;
    private String url;

    public Programmes(String name, String description, int imageResource, String url) {
        this.name = name;
        this.description = description;
        ImageResource = imageResource;
        this.url = url;
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

    public int getImageResource() {
        return ImageResource;
    }

    public void setImageResource(int imageResource) {
        ImageResource = imageResource;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Programmes{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", ImageResource=" + ImageResource +
                ", url='" + url + '\'' +
                '}';
    }
}
