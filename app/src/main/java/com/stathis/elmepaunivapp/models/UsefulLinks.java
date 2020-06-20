package com.stathis.elmepaunivapp.models;

public class UsefulLinks {

    private String name;
    private String description;
    private int ImageResource;

    public UsefulLinks(String name, String description, int imageResource) {
        this.name = name;
        this.description = description;
        ImageResource = imageResource;
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

    @Override
    public String toString() {
        return "UsefulLinks{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", ImageResource=" + ImageResource +
                '}';
    }
}
