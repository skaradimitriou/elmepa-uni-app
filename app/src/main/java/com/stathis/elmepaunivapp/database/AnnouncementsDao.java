package com.stathis.elmepaunivapp.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.stathis.elmepaunivapp.ui.announcements.model.Announcement;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface AnnouncementsDao {

    @Query("SELECT * FROM Announcements")
    public List<Announcement> getAll();

    @Insert
    void insert(ArrayList<Announcement> announcement);

    @Update
    void update(ArrayList<Announcement> announcement);

    @Delete
    void delete(Announcement announcement);

}
