package com.stathis.elmepaunivapp.ui.students.model;

public class UsefulLinks {

    private String name;
    private String url;
    private int ImageResource;

    public UsefulLinks(String name, String url, int imageResource) {
        this.name = name;
        this.url = url;
        ImageResource = imageResource;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getImageResource() {
        return ImageResource;
    }

    public void setImageResource(int imageResource) {
        ImageResource = imageResource;
    }

}
