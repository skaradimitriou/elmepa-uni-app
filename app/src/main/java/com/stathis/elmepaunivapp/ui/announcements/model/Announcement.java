package com.stathis.elmepaunivapp.ui.announcements.model;

public class Announcement {

    private String Name;
    private String OpenUrl;
    private String ImageResource;

    public Announcement(String name, String openUrl, String imageResource) {
        Name = name;
        OpenUrl = openUrl;
        ImageResource = imageResource;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getOpenUrl() {
        return OpenUrl;
    }

    public void setOpenUrl(String openUrl) {
        OpenUrl = openUrl;
    }

    public String getImageResource() {
        return ImageResource;
    }

    public void setImageResource(String imageResource) {
        ImageResource = imageResource;
    }

    @Override
    public String toString() {
        return "Announcement{" +
                "Name='" + Name + '\'' +
                ", OpenUrl='" + OpenUrl + '\'' +
                ", ImageResource='" + ImageResource + '\'' +
                '}';
    }
}
