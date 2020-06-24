package com.stathis.elmepaunivapp.models;

public class DeptPerks {

    private String name;
    private String url;
    private Integer img;

    public DeptPerks(String name, String url, Integer img) {
        this.name = name;
        this.url = url;
        this.img = img;
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

    public Integer getImg() {
        return img;
    }

    public void setImg(Integer img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "DeptPerks{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", img=" + img +
                '}';
    }
}
