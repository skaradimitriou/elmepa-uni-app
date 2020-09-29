package com.stathis.elmepaunivapp.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.stathis.elmepaunivapp.ui.announcements.model.Announcement;

@Database(entities = {Announcement.class}, exportSchema = false, version = 1)
public abstract class AnnouncementsDatabase extends RoomDatabase {

    private static AnnouncementsDatabase instance;

    @NonNull
    public static AnnouncementsDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), AnnouncementsDatabase.class, "AppAnnouncements.db")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return instance;
    }

    public abstract AnnouncementsDao getAnnouncementDao();
}
