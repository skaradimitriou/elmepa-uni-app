package com.stathis.elmepaunivapp.ui.announcements.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "Announcements")
public class Announcement {

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "name")
    @NonNull
    private String Name;

    @ColumnInfo(name = "openUrl")
    private String OpenUrl;

    @ColumnInfo(name = "imgResource")
    private String ImageResource;

    public Announcement(){

    }

    @Ignore
    public Announcement(@NonNull String name, String openUrl, String imageResource) {
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
